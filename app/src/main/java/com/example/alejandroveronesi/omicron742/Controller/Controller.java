package com.example.alejandroveronesi.omicron742.Controller;


import com.example.alejandroveronesi.omicron742.Model.DAO.DAOEvents;
import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.util.ResultListener;

import java.util.List;

public class Controller {

    public void getListPaints(final ResultListener<List<Event>> viewListener){
        DAOEvents daoEvents = new DAOEvents();
        daoEvents.obtainEvents(new ResultListener<List<Event>>() {
            @Override
            public void finish(List<Event> resultado) {
                if (resultado != null){
                    viewListener.finish(resultado);
                }
            }
        });


    }
}
