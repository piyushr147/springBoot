package enums;

public enum EnumSample implements ExtensionSample{
    WHITE(0,"i am white") {
        @Override
        public void call() {
            System.out.println("call to white");
        }

        @Override
        public void printType() {
            System.out.println("white type: " + type);
        }
        @Override
        public String toString() {
            return "white type: " + type + "description: " + description;
        }
    },
    BLACK(1,"i am black"){
        @Override
        public void call() {
            System.out.println("call to black");
        }

        @Override
        public void printDescription(){
            System.out.println("black type: " + type);
        }

        @Override
        public void printType() {
            System.out.println("black type: " + type);
        }

        @Override
        public String toString() {
            return "black type: " + type + "description: " + description;
        }
    };

    int type;
    String description;

    EnumSample(int type, String description){
        this.type = type;
        this.description = description;
    }

    public void printDescription(){
        System.out.println(description);
    }

    public abstract void printType();

    public static void main(String[] args) {
        EnumSample enumSample = EnumSample.WHITE;
        enumSample.printType();
        enumSample.printDescription();
        enumSample.call();
    }
}