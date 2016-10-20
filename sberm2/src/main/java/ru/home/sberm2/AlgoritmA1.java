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
        listSrednNeopr = jpa.findSpisSrednNeoprEntities(); // выбор списка всех неопределенных 
        Integer id = listSpisNotGender.get(0).getCustomerId();
        System.out.println(id);
        List<List<String>> listTest = jpa.selectSrednCustomer(id);// выбираем по ид ключевые коды пользователя
        float genderCustomer = 0;
        if (listTest == null) {// если нуль то вероятность = рандому, прсваевыем случайное значение
            Integer veroyatn = (int) (Math.random() + 0.5);// произвольное выпадение 0 или 1
            genderCustomer = veroyatn.floatValue();
        } else {
            List<Integer> veroyatAll = new ArrayList<>();
            for (List<String> list : listTest) { // перебираем ключевые коды пользователя
                System.out.println(list.get(0) + " " + list.get(1));
                int mcc_codeCust = Integer.valueOf(list.get(0));
                int srednCust = Integer.valueOf(list.get(1));

                for (SrednNeopr srednNeop : listSrednNeopr) { // перебор по таблице средний неопределенный
                    if (srednNeop.getMccCode() == mcc_codeCust) { // выбираем конктреную строку из выборки
                        float srNeopKoef = (float) (srednNeop.getSredn().floatValue() / 1.261);
                        if (srNeopKoef <= srednCust * srednNeop.getKoef()) {
                            Integer veroyat1 = 1;
                            veroyatAll.add(veroyat1);
                        } else {
                            Integer veroyat2 = 0;
                            veroyatAll.add(veroyat2);
                        }
                    }
                }
            }
            int itog = 0;
            for (int i = 0; i < veroyatAll.size(); i++) {
                itog += veroyatAll.get(0);
            }

            float verItog = itog / veroyatAll.size();
            genderCustomer = verItog;
        }

    }
}
