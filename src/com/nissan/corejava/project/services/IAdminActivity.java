package com.nissan.corejava.project.services;
/*interface for administrator activity which can be implemented for flight/bus/train 
  reservation system */
public interface IAdminActivity {
	public void add();
	public void schedule();
	public void edit();
	public void reschedule();
	public void cancel();
	public void viewSchedule();
	public void view();
}
