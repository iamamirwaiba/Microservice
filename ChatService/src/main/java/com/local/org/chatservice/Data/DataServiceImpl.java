package com.local.org.ChatService.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DataServiceImpl implements DataService{

    final Logger logger =
            LoggerFactory.getLogger(DataServiceImpl.class);


    private DataRepo dataRepo;

    @Autowired
    DataServiceImpl(DataRepo dataRepo){
        this.dataRepo=dataRepo;
    }


    @Override
    public DataEntity saveData(DataEntity data) {

        try {
            data.setCreatedAt(LocalDate.now());
            data.setUpdatedAt(LocalDate.now());
            dataRepo.save(data);
            return data;
        }

        catch (Exception e){
            logger.info("Failed to save data");
            return new DataEntity();
        }
    }

}
