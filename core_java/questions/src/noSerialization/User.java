package noSerialization;

import java.io.*;

class User implements Serializable {
    String username;
    transient String password;

    User(String u, String p) {
        username = u;
        password = p;
    }

    public static void main(String[] args) throws Exception {
        User u1 = new User("piyush", "secret");

        // Serialize
        ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream("data.ser"));
        out.writeObject(u1);
        out.close();

        // Deserialize
        ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("data.ser"));
        User u2 = (User) in.readObject();

        System.out.println(u2.username);
        System.out.println(u2.password);
    }
}


