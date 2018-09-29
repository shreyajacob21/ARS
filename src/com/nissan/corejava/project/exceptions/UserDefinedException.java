package com.nissan.corejava.project.exceptions;

public class UserDefinedException extends Exception
{
	String msg;
	public UserDefinedException(String msg) 
	{
		this.msg = msg;
	}
	public String toString(){
		return msg;
	}
}
