package com.sunil.booking.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunil.booking.enums.Status;
import lombok.Data;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class ResponseDTO<V> {

	String msg;
	
	Status status;
	
	V data;
	
	Integer totalRecords=new Integer(0);
	
	Integer records=new Integer(0);
	
	MetaData _meta;
	
	public ResponseDTO(V data)
	{
		this.data=data;
	}
	
	public ResponseDTO()
	{
	}
	
	public ResponseDTO(String msg,Status status,V data)
	{
		this.msg=msg;
		this.status=status;
		this.data=data;
		if(data != null)
		{
			this.totalRecords=1;
			this.records=1;
		}
	}
	
	public ResponseDTO(String msg,V data)
	{
		this.msg=msg;
		this.status= Status.PASS;
		this.data=data;
		if(data != null)
		{
			if(this.data instanceof ArrayList ){
				this.totalRecords=((ArrayList) this.data).size();
			} else {
				this.totalRecords = 1;
			}
			this.records=1;
		}
	}
	
	public ResponseDTO(String msg)
	{
		this.msg=msg;
		this.status=Status.PASS;
	}
	
	public ResponseDTO(String msg,V data,Integer totalRecords,Integer records)
	{
		this.msg=msg;
		this.status=Status.PASS;
		this.data=data;
		this.totalRecords=totalRecords;
		this.records=records;
	}
	
	public ResponseDTO(String msg,V data,Integer totalRecords,Integer records,Integer pageNumber,MetaData metaData)
	{
		this.msg=msg;
		this.status=Status.PASS;
		this.data=data;
		this._meta = metaData;
	}

	public ResponseDTO(String msg,Status status)
	{
		this.msg=msg;
		this.status=status;
	}
	

}
