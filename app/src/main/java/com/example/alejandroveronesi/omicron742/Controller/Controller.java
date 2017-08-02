package com.example.alejandroveronesi.omicron742.Controller;


import android.content.Context;

import com.example.alejandroveronesi.omicron742.Model.DAO.DAOEvents;
import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.util.ResultListener;

import java.util.List;

public class Controller {



    public void getEventList(final ResultListener<List<Event>> viewListener){
        DAOEvents daoEvents = new DAOEvents();
        daoEvents.obtainEventsFromDatabase(new ResultListener<List<Event>>() {
            @Override
            public void finish(List<Event> resultado) {
                if (resultado != null){
                    viewListener.finish(resultado);
                }
            }
        });
    }

    //metodo que reciba la noticia y le cambie el favorito
    public void agregador(Event event) {
        DAOEvents daoEvents = new DAOEvents();
        daoEvents.addEventToDatabase(event);
    }

}
