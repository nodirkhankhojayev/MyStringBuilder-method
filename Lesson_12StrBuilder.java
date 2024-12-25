import java.util.Arrays;

public class Lesson_12StrBuilder {
    public static void main(String[] args) {
        MyStringBuilder mySb = new MyStringBuilder("Hello");
        System.out.println(mySb);

        System.out.println();
        System.out.println("---append string method--");
        mySb.append("world");
        System.out.println(mySb);

        System.out.println();
        System.out.println("---append char array method--");
        mySb.append(new char[]{'!', '?', '.'});
        System.out.println(mySb);

        System.out.println();
        mySb = new MyStringBuilder("Hello world");
        System.out.println("---delete method--");
        mySb.delete(2, 4);
        System.out.println(mySb);

        System.out.println();
        mySb = new MyStringBuilder("Hello world");
        System.out.println("---deleteCharAt method--");
        mySb.deleteCharAt(6);
        System.out.println(mySb);

        System.out.println();
        mySb = new MyStringBuilder("Hello world");
        System.out.println("---indexOf method--");
        System.out.println(mySb.indexOf('l'));

        System.out.println();
        mySb = new MyStringBuilder("Hello world");
        System.out.println("---replace method--");
        mySb.replace(2, 4, "W");
        System.out.println(mySb);

        System.out.println();
        mySb = new MyStringBuilder("Hello world");
        System.out.println("---reverse method--");
        mySb.reverse();
        System.out.println(mySb);
    }
}

class MyStringBuilder {
    private char[] mass;
    private int length;
    private int count;

    public MyStringBuilder() {
        this.length = 16;
        this.mass = new char[length];
    }

    public MyStringBuilder(String str) {
        this.length = Math.max(16, str.length());
        this.mass = new char[length];
        append(str);
    }

    public MyStringBuilder append(String str) {
        ensureCapacity(count + str.length());
        for (int i = 0; i < str.length(); i++) {
            mass[count++] = str.charAt(i);
        }
        return this;
    }

    public MyStringBuilder append(char[] chars) {
        ensureCapacity(count + chars.length);
        for (char c : chars) {
            mass[count++] = c;
        }
        return this;
    }

    public MyStringBuilder delete(int start, int end) {
        if (start < 0 || start > count || end < 0 || end > count || start > end) {
            throw new IndexOutOfBoundsException("Invalid start or end index");
        }
        System.arraycopy(mass, end, mass, start, count - end);
        count -= (end - start);
        return this;
    }

    public MyStringBuilder deleteCharAt(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        System.arraycopy(mass, index + 1, mass, index, count - index - 1);
        count--;
        return this;
    }


    public int indexOf(char c) {
        for (int i = 0; i < count; i++) {
            if (mass[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        int strLen = str.length();
        for (int i = 0; i <= count - strLen; i++) {
            int j;
            for (j = 0; j < strLen; j++) {
                if (mass[i + j] != str.charAt(j)) {
                    break;
                }
            }
            if (j == strLen) {
                return i;
            }
        }
        return -1;
    }

    public MyStringBuilder replace(int start, int end, String str) {
        if (start < 0 || start > count || end < 0 || end > count || start > end) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        delete(start, end);
        ensureCapacity(count + str.length());
        System.arraycopy(mass, start, mass, start + str.length(), count - start);
        for (int i = 0; i < str.length(); i++) {
            mass[start + i] = str.charAt(i);
        }
        count += str.length();
        return this;
    }

    public MyStringBuilder reverse() {
        for (int i = 0; i < count / 2; i++) {
            char temp = mass[i];
            mass[i] = mass[count - 1 - i];
            mass[count - 1 - i] = temp;
        }
        return this;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > length) {
            length = length + (length >> 1);
            if (length < minCapacity) {
                length = minCapacity;
            }
            mass = Arrays.copyOf(mass, length);
        }
    }

    @Override
    public String toString() {
        return new String(mass, 0, count);
    }
}