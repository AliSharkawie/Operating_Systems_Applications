public class Process {
    public String name;
    public int size;

    public Process(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int isFit(Partition p) {
        if (p.size == this.size) return 1;
        else if (p.size > this.size) return 2;
        else return 0;
    }

}
