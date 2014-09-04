class HelloWorld {
    public native void print();

    static {
       // System.loadLibrary("X:\\Working\\TS\\Test\\TestJNI\\MyJNI.dll");
        System.load("X:\\Working\\TS\\Test\\TestJNI\\MyJNI.dll");

    }

    public static void main(String[] args) {
        new HelloWorld().print();
    }
}

