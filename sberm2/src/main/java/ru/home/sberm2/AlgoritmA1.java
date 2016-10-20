/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.sberm2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import ru.home.entity.SpisNotGenderCustom;
import ru.home.entity.SpisNotGenderCustomJpaController;
import ru.home.entity.SrednNeopr;

/**
 *
 * @author олег
 */
public class AlgoritmA1 {

    private List<SpisNotGenderCustom> listSpisNotGender;
    private List<SrednNeopr> listSrednNeopr;

    public static void main(String[] args) {
        AlgoritmA1 aa = new AlgoritmA1();
        aa.runAlgoritm();

    }

    public void runAlgoritm() {
        // 1 получаем список неопределенных пользоватлей
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sber1");
        SpisNotGenderCustomJpaController jpa = new SpisNotGenderCustomJpaController(emf);
        listSpisNotGender = jpa.findSpisNotGenderCustomEntities();
        for (SpisNotGenderCustom spisNotGenderCustom : listSpisNotGender) {// перебираем всех неопределенных пользователей
          
            listSrednNeopr = jpa.findSpisSrednNeoprEntities(); // выбор списка всех средних, коэф. и их мсс кодов  
            Integer id = spisNotGenderCustom.getCustomerId(); // получение ид пользоватлея
           // System.out.println(id);
            List<List<String>> listTest = jpa.selectSrednCustomer(id);// выбираем по ид ключевые коды пользователя
            float genderCustomer = 0;// пол пользоватлея по умолчанию Ж
            // Проверка на случай если у пользователя нет ключевых кодов (они выбирались по наиболее сильным отличиям М и Ж)
            
            if (listTest == null || listTest.isEmpty() ) {// если нуль то вероятность = рандому, присваеваем случайное значение
                Integer veroyatn = (int) (Math.random() + 0.5);// произвольное выпадение 0 или 1
                genderCustomer = veroyatn.floatValue();
            } else {// иначе
                List<Integer> veroyatAll = new ArrayList<>(); // список ключевых пользователя, для каждого из них вероятность разная (итог по среднему значению)
                for (List<String> list : listTest) { // перебираем ключевые коды пользователя
                   // System.out.println(list.get(0) + " " + list.get(1));
                    int mcc_codeCust = Integer.valueOf(list.get(0));
                    int srednCust = Integer.valueOf(list.get(1));

                    for (SrednNeopr srednNeop : listSrednNeopr) { // перебор по таблице средний неопределенный(все ключ. значения)
                        if (srednNeop.getMccCode() == mcc_codeCust) { // выбираем конктреную строку из выборки
                            
                            float srNeopKoef = (float) (srednNeop.getSredn().floatValue() / 1.261); // допущение 1 муж больше
                            System.out.println("kod = "+mcc_codeCust+ " value = "+srNeopKoef+" ? "+srednCust * srednNeop.getKoef());
                            if (srNeopKoef <= srednCust * srednNeop.getKoef()) {// если среднее меньше то М
                                Integer veroyat1 = 1;
                                veroyatAll.add(veroyat1);
                            } 
                           if (srNeopKoef > srednCust * srednNeop.getKoef())  {// если нет то Ж
                                Integer veroyat2 = 0;
                                System.out.println("Ж");
                                veroyatAll.add(veroyat2);
                            }
                        }
                    }
                }
                int itog = 0;
                for (int i = 0; i < veroyatAll.size(); i++) {
                    itog += veroyatAll.get(0);
                }
               // System.out.println(id+" "+veroyatAll.size());
              // System.out.println(itog+" "+veroyatAll.size());
                float verItog = itog / veroyatAll.size();
                
                genderCustomer = verItog;
            }
            System.out.println(id +" "+genderCustomer);
        } 
        
    }
}
