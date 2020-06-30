package com.djk.common;

import java.util.concurrent.atomic.AtomicInteger;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class TestThread extends Thread{
	
	public static volatile AtomicInteger num=new AtomicInteger(0);
	
	public void addCount() {
		for(int i=0; i<1000; i++) {
			num.addAndGet(1);
		}
		System.out.println("i="+num);
	}
	
	@Override
	public void run() {
		addCount();
	}
	
	public static void main(String[] args) {
		TestThread [] s = new TestThread[10];
		for(int i=0; i<s.length; i++) {
			s[i] = new TestThread();
		}
		for(int i=0; i<s.length; i++) {
			s[i].start();
		}
	}
}
