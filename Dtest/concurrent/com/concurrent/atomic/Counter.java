package com.concurrent.atomic;

public interface Counter {

	int incrementAngGet();
	int decrementAndGet();
	
	int addAngGet(int delta);
	
	int get();
}
