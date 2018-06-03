package sweethome;

public class Price {

    public static class Id {

        private String code;
        private long id;
        private int dep;

        public Id(String code_, long id_, int dep_) {
            code = code_;
            id   = id_;
            dep  = dep_;
        }

        public String getCode() { return code; }
        public long getId() { return id; }
        public int getDep() { return dep; }

        public String info() {
            return code + "  " + id + "  " + dep;
        }

        @Override
        public int hashCode() {
            return (code + id + dep).hashCode();
        }

        @Override
        public boolean equals(Object obj) {

            if (!(obj instanceof Id))
                return false;
            if (obj == this)
                return true;

            return hashCode() == ((Id) obj).hashCode();
        }
    }

    private Id id;
    private Date start;
    private Date finish;
    private long value;

    public Price(String code_, long id_, int dep_, Date start_, Date finish_, long value_) {
        id     = new Id(code_, id_, dep_);
        start  = start_;
        finish = finish_;
        value  = value_;
    }

    public Price(Price other) {
        id     = other.id;
        start  = other.start;
        finish = other.finish;
        value  = other.value;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (other == null) return false;
        if(this.getClass() != other.getClass()) return false;

        Price price = (Price) other;

        if (!id.equals(price.id)) return false;

        return start.compareTo(price.start) == 0 && finish.compareTo(price.finish) == 0 && value == price.value;
    }

    public Id getId() {
        return id;
    }
    public long getVal() {
        return value;
    }

    public Date getStart() {
        return start;
    }
    public Date getFinish() {
        return finish;
    }

    public void setStart(Date date) { start = date; }
    public void setFinish(Date date) { finish = date; }

    public void changeStart(int amount) { start.add(amount); }
    public void changeFinish(int amount) { finish.add(amount); }

    public void info() {
        System.out.format("%16s%16d%16d%24s%24s%16d\n", id.getCode(), id.getId(), id.getDep(),
                                                           start.toString(), finish.toString(), value);
    }
}