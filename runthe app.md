
Project Structure


src/
  model/
    Customer.java
    Parcel.java
    QueofCustomers.java
    ParcelMap.java
  controller/
    Worker.java
    Manager.java
  view/
    MainFrame.java
    ParcelsPanel.java
    CustomerQueuePanel.java
    ProcessingPanel.java
  utils/
    Log.java
    
To Compile and Run

In Eclipse , right-click the Manager.java file and select Run (or Debug).
From the command line, compile everything, then run:

javac model/*.java controller/*.java view/*.java utils/*.java
java controller.Manager
Interact with the GUI

You will see three panels (Parcels, Customer Queue, Worker Processing).
you can call Log.getInstance().saveToFile("log.txt") at the end of main or during app shutdown to store logs to a file.
