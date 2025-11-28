import java.util.Scanner;

public class TodolistApp {

    public static String[] model = new String[10];
    public static java.util.Scanner scanner =new java.util.Scanner(System.in);

    public static void main(String[] args) {
        viewShowTodoList();
    }

    /**
     * Fungsi untuk menampilkan todo list
     */
    public static void showTodoList() {
        for (var i = 0; i < model.length; i++) {
            var no = i + 1;
            var todo = model[i];

            if (todo != null) {
                System.out.println(no + ". " + todo);
            }
        }
    }

    /**
     * Fungsi untuk test fungsi showTodoList
     */
    public static void testShowTodoList() {
        for (var i = 0; i < 5; i++) {
            model[i] = "task " + i;
        }
        showTodoList();
    }

    /**
     * Fungsi untuk menambahkan todo ke list
     */
    public static void addTodoList(String todo) {
        // Cek apakah array sudah penuh atau belum
        var isFull = true;
        for (var i = 0; i < model.length; i++) {
            if (model[i] == null) {
                isFull = false;
                break;
            }
        }

        if (isFull) {
            var temp = model;
            model = new String[model.length * 2];
            for (var i = 0; i < temp.length; i++) {
                model[i] = temp[i];
            }
        }

        for (var i = 0; i < model.length; i++) {
            if (model[i] == null) {
                model[i] = todo;
                break;
            }
        }
    }

    public static void testAddTodoList() {
        for (var i = 0; i < 25; i++) {
            addTodoList("Task-"+i);
        }

        showTodoList();
    }

    /**
     * Fungsi untuk menghapus todo dari list
     */
    public static boolean removeTodoList(int no) {
        // penjagaan kalo input no yang gaada
        if ((no-1) >= model.length) {
            return false;
        } else if (model[no-1] == null) {
            return false;
        } else {
            for (var i = (no-1); i < model.length; i++) {
                if (i == (model.length-1)) {
                    model[i] = null;
                } else {
                    model[i] = model[i+1];
                }
            }
            return true;
        }
    }

    public static void testRemoveTodoList() {
        addTodoList("Java");
        addTodoList("PHP");
        addTodoList("Golang");
        addTodoList("Rust");

        System.out.println("Before Delete");
        showTodoList();

        removeTodoList(2);

        System.out.println("Setelah delete");
        showTodoList();

    }

    public static String input(String info) {
        System.out.print(info + " : ");
        String data = scanner.nextLine();
        return data;
    }

    public static void testInput() {
        var result = input("Masukkan nama anda");
        System.out.println(result);
    }

    /**
     * Tampilan untuk show todo list
     */
    public static void viewShowTodoList() {
        while(true) {
            System.out.println("TODOLIST");

            showTodoList();

            System.out.println("MENU");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("x. Keluar");

            var userChoice = input("Masukan pilihan anda");

            if (userChoice.equals("1")) {
                viewAddTodoList();
            } else if (userChoice.equals("2")) {
                viewRemoveTodoList();
            } else if (userChoice.equals("x") || userChoice.equals("X")) {
                break;
            } else {
                System.out.println("Pilihan anda tidak ada");
            }
        }
    }

    public static void testViewShowTodoList() {
        addTodoList("Java");
        addTodoList("Golang");
        addTodoList("Rust");
        addTodoList("PHP");

        viewShowTodoList();
    }

    /**
     * Tampilan untuk add todo list
     */
    public static void viewAddTodoList() {
        System.out.println("TAMBAH TODO");
        var todo = input("Masukan todo (x untuk batal)");

        if (todo.equals("x") || todo.equals("X")) {
            // batal
        } else {
            addTodoList(todo);
        }
    }

    /**
     * Tampilan untuk remove todo list
     */
    public static void viewRemoveTodoList() {
        System.out.println("HAPUS TODO");
        showTodoList();
        var number = input("Masukan nomor todo (x untuk batal");
        if (number.equals("x") || number.equals("X")) {
            // batal
        } else {
            boolean success = removeTodoList(Integer.valueOf(number));
            if (!success) {
                System.out.println("Gagal menghapus todo no " + number);
            }
        }
    }
}
