package com.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Main
{
    public static void main(String[] args) {
        try {
            Airport.initializeSearchable(); //Считываем широту и долготу с консоли
            HashSet<String> CSVLines = AirportParser.readFromCSV(); //Считываем данные из файла
            ArrayList<Airport> airports = AirportParser.parseAirports(CSVLines); //Парсим объекты Аэропорт
            LocalTime lt1 = LocalTime.now(); //Засекаем время, потраченное на поиск
            AirportParser.findNearest(airports, 5); //Находим необходимое количество ближайших аэропортов
            LocalTime lt2 = LocalTime.now();
            Duration duration = Duration.between(lt1, lt2);
            System.out.println("Затраченное время: " + duration.toMillis()); //Выводим затраченное время

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }



    }
}
