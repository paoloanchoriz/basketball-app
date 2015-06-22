package com.panchoriz.myapp.constants;

public enum Status {
	
	// Pending - needs to complete minimum players, default is 10
	PENDING, 
	// Cancelled - organizer invoked
	CANCELLED,
	// Closed - max players met
	CLOSED, 
	// Open - min players met, max players not yet met
	OPEN;
}
