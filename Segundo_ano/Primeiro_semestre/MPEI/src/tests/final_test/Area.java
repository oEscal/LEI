package tests.final_test;

import processing.core.PApplet;

import java.util.TreeSet;

public class Area extends PApplet {
    private int x, y, w, h;
    private static int i;
    private int id;
    TreeSet<Integer> pixels;
    PApplet pa;

    Area(int x, int y, int w, int h, PApplet p) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.i++;
        this.id = i;
        this.pa = p;
        pixels = new TreeSet<>();

    }

    public boolean mouseIn(int x, int y) {
        if (x >= this.x && x <= this.x + this.w && y >= this.y && y <= this.y + this.h) {
            return true;
        }
        return false;

    }

    public void addPixel(TreeSet<Point> p) {
        TreeSet<Integer> h = new TreeSet<>();
        Point[] po = p.toArray(new Point[p.size()]);
        for (int i = 0; i < po.length; i++) {
            if (this.x!=this.x+this.w && this.y!=this.y+this.h){
                h.add((int)(Double.parseDouble(String.format("%d.%d", (int) map(po[i].getX(), this.x, this.x + this.w, 0, 28), (int) map(po[i].getY(), this.y, this.y + this.h, 0, 28)))*100));
                h.add((int)(Double.parseDouble(String.format("%d.%d", (int) map(po[i].getX(), this.x, this.x + this.w, 0, 28) + 1, (int) map(po[i].getY(), this.y, this.y + this.h, 0, 28) + 1))*100));
                h.add((int)(Double.parseDouble(String.format("%d.%d", (int) map(po[i].getX(), this.x, this.x + this.w, 0, 28) - 1, (int) map(po[i].getY(), this.y, this.y + this.h, 0, 28) - 1))*100));
            }
        }
        this.pixels.addAll(h);

    }

    public TreeSet<Integer> getPixels() {
        return this.pixels;
    }


    public void resetID() {
        this.i = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }

    public int getH() {
        return this.h;
    }

    public String toString() {
        StringBuilder br = new StringBuilder();
        br.append("x-->" + this.x + "\ty-->" + this.y + "\tw-->" + this.w + "\th-->" + this.h + "\tID-->" + this.id + "\n");
        return br.toString();
    }
}
