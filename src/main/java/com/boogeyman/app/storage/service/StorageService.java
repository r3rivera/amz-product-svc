package com.boogeyman.app.storage.service;

public interface StorageService<I, R>{
     R createRecord(I record);
}
