package com.example.tiktokapp.responseModel;

import java.sql.Timestamp;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
abstract class AbstractModel {
     private int id;
     private Timestamp createdAt;
     private Timestamp updatedAt;

     public AbstractModel(int id, Timestamp createdAt, Timestamp updatedAt) {
          this.id = id;
          this.createdAt = createdAt;
          this.updatedAt = updatedAt;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public Timestamp getCreatedAt() {
          return createdAt;
     }

     public void setCreatedAt(Timestamp createdAt) {
          this.createdAt = createdAt;
     }

     public Timestamp getUpdatedAt() {
          return updatedAt;
     }

     public void setUpdatedAt(Timestamp updatedAt) {
          this.updatedAt = updatedAt;
     }
}
