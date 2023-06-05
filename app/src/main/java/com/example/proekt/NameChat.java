package com.example.proekt;

public class NameChat {
    String name;
    String name_res;

    public NameChat() {}

    public NameChat(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        char vector_chat = name.charAt(0);
        char type_chat = name.charAt(1);
        char number_chat = name.charAt(2);
        if (vector_chat == 'p') {
            name_res = "Профиль.";
        }
        else {
            name_res = "База.";
        }
        if (type_chat == 'w') {
            name_res += "Вариант.";
        }
        else {
            name_res += "Задание.";
        }
        name_res += number_chat;
    }

    public String getName_res() {
        return this.name_res;
    }
}
