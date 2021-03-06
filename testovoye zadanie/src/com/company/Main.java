package com.company;

import java.util.Scanner;


// основной метод на (129)




public class Main
{
    private static int StrToInt(String a){ // получаем string, возвращаем int
        int i = Integer.parseInt(a);
        return i;
    }



    private static String[] VvodTexta() { // принимаем у пользователя выражение вида а + б и
        System.out.println("Введите выражение: "); // формируем из него массив вида ["а", "+", "б"]
        Scanner s = new Scanner(System.in);
        String inp = s.nextLine();
        String[] words = inp.split(" ");
        return(words);
    }
    private static int[] Vychislenie() {
        String[] m = VvodTexta(); // получаем массив с нашим выражением вида ["1", "+", "2"] 
        int first, second; // переменные для обозначения певрого и второго слагаемого
        String znak; // переменная для знака операции
        int m2[]; // конечный массив, который будем передавать
        m2 = RimskyCheck(m); // проверяем на римские буквы и если они есть, меняем их на цифры 
        if ((m.length) > 3){ // проверяем, ввел ли пользователь только два числа и один знак
            m2[3] = 1000;
        }
        if (m2[3] == 1000){ // если ошибка уже есть, сразу выводим массив и экономим немного времени
            return m2;
        }
        first = m2[0]; // первое слагаемое
        second = m2[2]; // второе слагаемое
        znak = m[1]; // знак выражения
        if (((first < 1) | (first > 10) | (second < 1) | (second > 10))){ // оба слагаемых должны быть "от 1 до 10 включительно"
            m2[3] = 1000; // если нет, передаем сообщение об ошибке
            return m2;
        }


        if (znak.equals("+")) // производим вычисления в зависимости от знака
            m2[0] = Math.round(first + second);
        if (znak.equals("-"))
            m2[0] = Math.round(first - second);
        if (znak.equals("*"))
            m2[0] = Math.round(first * second);
        if (znak.equals("/"))
            m2[0] = Math.round(first / second);
        // как вы могли заметить, конечный результат мы поместили на первое(нулевое) место в массиве

        /*if ((m2[4] == 0) && (m2[0] > 0)){ // если введены арабские числа, а ответ получился > нуля,
            m2[3] = 1000; // передаем сообщение об ошибке

        }*/
        if ((m2[4] == 1) && (m2[0] <= 0)){ // если введены римские числа, а ответ получился <= нуля,
            m2[3] = 1000; // передаем сообщение об ошибке

        }
        return(m2);

    }

    private static int[] RimskyCheck(String[] m) {
        int f = 0, s = 0; // счетчики для проверки слагаемых на римские цифры
        int[] m2 = {0, 0, 0, 0, 0}; // помимо места для слагаемых и знака, добавились еще несколько: m2[3] сообщает нам есть ли ошибка, m2[4] рассказывает об использовании римских цифр
        String[] rimskyBukvi = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",};


        for (int i = 1; i <= 10; i++) {
            if (m[0].equals(rimskyBukvi[(i - 1)])) { // если на n-ной итерации цикла мы замечаем совпадение с буквой в массиве rimskyBukvi
                m2[0] = i; // заменяем число n на римскую цифру, соответствующую по значению
                f += 1; // увеличиваем счетчик, тем самым показывая, что перемнная номер один была заменена
                m2[4] = 1;  // показываем, что использовались римские цифры (это нам пригодится для вывода ответа в римских цифрах)
                // обратите внимание, что передаем мы числа в формате int
            }
            if (m[2].equals(rimskyBukvi[(i - 1)])) { // аналогично со второй переменной
                m2[2] = i;
                s += 1;
                m2[4] = 1;
            }
        }
        if ((f > 0) && (s > 0)) { // если оба слагаемых пользователь ввел римскими цифрами
            return m2;  // вовзращаем наш массив в котором римские цифры заменены на арабские
        }

        if ((f == 0) && (s == 0)) { // если ОБА числа пользователь ввел в "арабском" виде
            m2[0] = StrToInt(m[0]); // меняем их тип со str на int (13)
            m2[2] = StrToInt(m[2]);
            return m2; // и возвращаем массив (здесь m2[4] = 0, следовательно ответ будем выводить арабскими цифрами
        }
        if (((StrToInt(m[0]) % 1) > 0) | ((StrToInt(m[2]) % 1) > 0)) { // если у введенного числа есть числа после запятой
            m2[3] = 1000; // присваем положительное значение элементу, который хранит сведения об ошибке
            return m2; // и возвращаем массив
        }

        m2[3] = 1000; // во всех других случаях так же должна быть ошибка
        return m2;


    }


    private static String FiveToV(int a){ // все просто: допустим мы получили ответ 9
            String[] rimskyBukvi = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
            return (rimskyBukvi[(a - 1)]); // тогда будем передавать элемент массива под номером 8
        } // а т.к. отсчет начинается с 0, то передастся "IX"

    private static String Vyvod() {
        int m2[] = Vychislenie(); // получаем массив с вычисленным ответом, сообщением об ошибках и проверкой на ввод римских цифры 
        int x = m2[0]; // отдельной переменной присваиваем ответ
//        }
        if (m2[3] > 0){ // m2[3] > 0, если какое-то из требований задания было нарушено
            System.out.println(10/0); // возвращаем сообщение об ошибке
        }
        if (m2[4] > 0){ // если пользователь вводил римскими цифрами
            return FiveToV(x); // выводим тоже римскими 
        }
        return (Integer.toString(x)); // во всех других случаях выводим в арабском виде в строковом типе

    }

    public static void main(String[] args) { // вызываем функцию и решаем выражения
        System.out.println(Vyvod()); 

        }


}
