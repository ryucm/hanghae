public class PublicTransport {
    private int number;
    private int fuel;
    private int speed;
    private int fare;
    private int passenger;
    private Boolean status;

    public int getNumber() {return number;}

    public Boolean getStatus() {return status;}

    public int getFare() {return fare;}

    public int getFuel() {return fuel;}

    public int getSpeed() {return speed;}

    public int getPassenger() {return passenger;}

    public void setFuel(int fuel) {
        this.fuel = fuel;
        if (this.fuel < 10){
            setStatus(false);
        }
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setPassenger(int passenger) {
        this.passenger += passenger;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public PublicTransport(int number) {
        this.number = number;
        this.fuel = 100;
        this.speed = 0;
    }

    public Boolean isdrive(){
        if ((this.fuel >= 10) & this.status){
            return true;
        } else {
            return false;
        }
    }

    public void run() {
        if (!this.isdrive()){
            System.out.println("주유가 필요합니다");
            System.out.printf("%s %d","현재 연료:", this.fuel);
        } else {
            this.status = true;
        }
    }

    public void stop(){
        this.status = false;
    }

    public void changeSpeed(int speed) {
        if (this.isdrive()){
            this.speed += speed;
            System.out.printf("%s %d","현재 속도", this.speed);
        } else {
            System.out.println("주유량을 확인해 주세요");
        }
    }


}

class Bus extends PublicTransport {
    int maxPassenger = 30;

    Bus(int number) {
        super(number);
        setStatus(true);
        setFuel(100);
        setFare(1000);
    }

    @Override
    public String toString() {
        System.out.println("탑승 승객 : " + getPassenger());
        System.out.println("잔여 승객 : " + possibleRide());
        System.out.println("요금 확인 : " + getFare() * getPassenger());
        System.out.println("연료 확인 : " + getFuel());
        System.out.println("상태 : " + status());
        if (getFuel() < 10){
            System.out.println("주유 필요");
        }
        return "끝";
    }

    public String status(){
        if (getStatus()){
            return "운행중";
        } else {
            return "차고지행";
        }
    }
    public int possibleRide(){
        return this.maxPassenger - getPassenger();
    }

    public void addPassenger(int num){
        if (getStatus() & possibleRide() > num) {
            setPassenger(num);
        } else {
            System.out.println("최대 승객 수 초과");
        }
    }

    public void spendFuel(int num){
        setFuel(getFuel()-num);
    }

    public void chargeFuel(int num){
        setFuel(getFuel()+num);
    }

    public void locate(String str) {
        if (str == "차고지행"){
            setStatus(false);
            setPassenger(-getPassenger());
        } else{
            setStatus(true);
        }
    }

}

class Taxi extends PublicTransport {
    private int maxPassenger = 4;
    private int meterFare = 1000;
    private int meter = 1;
    private String destination;
    private int distance;

    private int sum;

    Taxi(int number) {
        super(number);
        setStatus(true);
        setFuel(100);
        setFare(3000);
    }

    public int getMaxPassenger() {
        return maxPassenger;
    }

    public String getDestination() {
        return destination;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMeterFare() {
        return meterFare;
    }

    public int getMeter() {
        return meter;
    }



//    public int
    public String status(){
        if (getStatus()){
            return "일반";
        } else {
            return "운행 중";
        }
    }


    public String toString() {
        System.out.println("탑승 승객 : " + getPassenger());
        System.out.println("잔여 승객 : " + (getMaxPassenger() - getPassenger()));
        System.out.println("주유량 : " + getFuel());
        if (getFuel() == 0){
            System.out.println("상태 : "+ "운행 불가");
            System.out.println("누적 요금 : " + getSum());
            return "주유 필요";
        } else{
            System.out.println("상태 : " + status());
        }
        System.out.println("기본 요금 확인 : " + getFare());
        System.out.println("목적지 : " + getDestination());
        System.out.println("목적지까지 거리 : " + getDistance());
        if (getStatus()){
            System.out.println("지불할 요금 : " + 0);
        } else {
            System.out.println("지불할 요금 : " + amount());
        }
        System.out.println("누적 요금 : " + getSum());
        System.out.println("상태 : " + status());

        return "끝";
    }

    public void spendFuel(int num){
        setFuel(getFuel()-num);
    }

    public void chargeFuel(int num){
        setFuel(getFuel()+num);
    }

    public void ride(int num, String destination, int distance) {
        if(getStatus() & maxPassenger > getPassenger()+num){
            setPassenger(num);
            setStatus(false);

            setDestination(destination);
            setDistance(distance);
        } else{
            System.out.println("최대 승객 수 초과");
        }
    }
    public int amount(){
        return (getMeterFare()*getMeter()*(getDistance()-1) + getFare());
    }

    public void ride(int num) {
        if(getStatus() & maxPassenger > getPassenger()+num){
            setPassenger(num);
            setStatus(false);

            setDestination(destination);
            setDistance(distance);
        } else{
            System.out.println("최대 승객 수 초과");
        }
    }

    public void takeOff(){
        setSum(getSum()+amount());
        setStatus(true);
        setPassenger(-getPassenger());
        setDestination(null);
        setDistance(0);

    }
}

