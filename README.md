# Simple Distributed Load Balancer Simulation 

### Introduction
This is a simulations of the implementation of the software Load Balancer that routes TCP network calls to different
running instances for our radius calculating server in a multithreaded manner.

### Enhancements Coming
- Round-Robin routing
- Sticky Round-Robin routing
- Health Checks

### Round-Robin 
This algorithm is implemented using a local customized linked-list using the next pointer as a needle that points to the 
next node server as a client request is being executed. If client requests moves the node to the tail node, the pointer
routes back to the head. Would be easier to make it a circular linked-list.

### Sticky-Round-Robin

### Issues
- Faults
- Thread sleeps to avoid collisions
- Probable unnecessary use of locks

### Technologies Used
- Java
- Socket and ServerSockets
- Threads using Runnable interface

### Author
[Burmau Garba](https://github.com/BURMAUG?tab=repositories)
