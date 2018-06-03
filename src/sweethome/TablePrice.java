package sweethome;

import java.util.*;

public class TablePrice {

    private enum Pos {LEFT, INSIDE, RIGHT}

    private Map<Price.Id, LinkedList<Price>> table;

    public TablePrice() {
        table = new HashMap<>();
    }

    static TablePrice create(List<Price> prices) {

        TablePrice table = new TablePrice();
        table.addAll(prices);

        return table;
    }

    public List<Price> getById(String code, long num, int dep) {

        Price.Id id = new Price.Id(code, num, dep);
        return table.get(id);
    }

    public long countById(String code, long num, int dep) {

        Price.Id id = new Price.Id(code, num, dep);
        return table.get(id).size();
    }

    public static TablePrice join(List<Price> prices1, List<Price> prices2) {

        TablePrice prices = new TablePrice();

        prices.addAll(prices1);
        prices.addAll(prices2);

        return prices;
    }

    public void addAll(List<Price> prices) {
        prices.forEach(this::add);
    }

    public void add(Price price) {

        Price.Id id = price.getId();

        if (table.containsKey(id)) {
            LinkedList<Price> update_prices = insert(table.get(id), price);
            table.replace(id, update_prices);
        } else {
            LinkedList<Price> prices = new LinkedList<>();
            prices.add(price);
            table.put(id, prices);
        }
    }

    private LinkedList<Price> insert(LinkedList<Price> prices, Price new_price) {

        Date start  = new_price.getStart();
        Date finish = new_price.getFinish();

        Date date1, date2;
        Pos  pos1,  pos2;

        LinkedList<Price> new_right_prices = new LinkedList<>();
        LinkedList<Price> new_left_prices  = new LinkedList<>();

        new_right_prices.add(new_price);

        for (Price price : prices) {

            date1 = price.getStart();
            date2 = price.getFinish();

            pos1 = define_pos(date1, start, finish);
            pos2 = define_pos(date2, start, finish);

            if (pos1 == Pos.LEFT) {
                switch (pos2) {
                    case LEFT:
                        new_left_prices.add(price);
                        break;

                    case INSIDE:
                        if (price.getVal() == new_price.getVal()) {
                            new_price.setStart(price.getStart());
                        } else {
                            price.setFinish(new Date(new_price.getStart()));
                            price.changeFinish(-1);
                            new_left_prices.add(price);
                        }
                        break;

                    case RIGHT:
                        if (price.getVal() == new_price.getVal()) {
                            new_price.setStart(price.getStart());
                            new_price.setFinish(price.getFinish());
                        } else {
                            Price left_price  = price;
                            Price right_price = new Price(price);

                            left_price.setFinish(new Date(new_price.getStart()));
                            left_price.changeFinish(-1);

                            right_price.setStart(new Date(new_price.getFinish()));
                            right_price.changeStart(+1);

                            new_right_prices.addFirst(left_price);
                            new_right_prices.add(right_price);
                        }
                        break;
                }
            }

            if (pos2 == Pos.RIGHT) {
                switch (pos1) {
                    case INSIDE:
                        if (price.getVal() == new_price.getVal()) {
                            new_price.setFinish(price.getFinish());
                        } else {
                            price.setStart(new Date(new_price.getFinish()));
                            price.changeStart(+1);
                            new_right_prices.add(price);
                        }
                        break;

                    case RIGHT:
                        new_right_prices.add(price);
                        break;
                }
            }
        }

        new_left_prices.addAll(new_right_prices);

        return new_left_prices;
    }

    private Pos define_pos(Date date, Date left, Date right) {

        int res1 = date.compareTo(left);
        int res2 = date.compareTo(right);

        if (res1 < 0)
            return Pos.LEFT;

        if (res1 >= 0 && res2 <= 0)
            return Pos.INSIDE;

        return Pos.RIGHT;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (other == null) return false;
        if(this.getClass() != other.getClass()) return false;

        TablePrice prices = (TablePrice) other;

        int size       = table.size();
        int other_size = prices.table.size();

        if (size != other_size) return false;

        Set<Price.Id> ids       = table.keySet();
        Set<Price.Id> other_ids = prices.table.keySet();

        if (!ids.containsAll(other_ids)) return false;
        if (!other_ids.containsAll(ids)) return false;

        LinkedList<Price>   prices1, prices2;
        ListIterator<Price> iter1,   iter2;

        for (Price.Id id : ids) {
             prices1 = table.get(id);
             prices2 = prices.table.get(id);

            iter1 = prices1.listIterator();
            iter2 = prices2.listIterator();

             while (iter1.hasNext()) {
                 if (!iter1.next().equals(iter2.next())) return false;
             }
        }

        return true;
    }

    void print() {

        if (table == null) return ;

        System.out.format("%16s%16s%16s%24s%24s%16s\n", "Code", "Number", "Department",
                "Begin", "End", "Price");

        table.forEach((id, prices) -> {
            prices.forEach(Price::info);
        });
    }
}
