package model;

public class Worker {
    private String workerName;

    public Worker(String workerName) {
        this.workerName = workerName;
    }

    public double calculateFee(Parcel parcel) {
        // Example fee calculation
        return parcel.getWeight() * 0.5 + parcel.getDistance() * 0.2;
    }


    public void processCustomer(Customer customer, Parcel parcel, double fee) {
        Log.getInstance().logEvent("Worker " + workerName + " processed customer " + customer.getName() +
                " for parcel " + parcel.getParcelId() + ". Fee: $" + String.format("%.2f", fee));
        
    }
}
