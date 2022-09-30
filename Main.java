public class Main {

    public static void main(String[] args) {
        // Bus
//        Bus bus1 = new Bus(100);
//        Bus bus2 = new Bus(200);
//        System.out.println("생성된 버스 :"+ bus1.getNumber());
//        System.out.println("생성된 버스 :"+ bus2.getNumber());
//        bus1.addPassenger(2);
//        System.out.println(bus1);
//        bus1.spendFuel(50);
//        System.out.println(bus1);
//        bus1.locate("차고지행");
//        bus1.chargeFuel(10);
//        bus1.locate("운행중");
//        bus1.addPassenger(45);
//        bus1.addPassenger(5);
//        System.out.println(bus1);
//        bus1.spendFuel(55);
//        System.out.println(bus1);

        // Taxi
        Taxi taxi1 = new Taxi(0001);
        Taxi taxi2 = new Taxi(1000);
        System.out.println(taxi2);
        taxi2.ride(2, "서울역", 2);
        System.out.println(taxi2);
        taxi2.spendFuel(80);
        taxi2.takeOff();
        System.out.println(taxi2);
        taxi2.ride(5);
        taxi2.ride(3, "구로디지털단지역", 12);
        System.out.println(taxi2);
        taxi2.spendFuel(20);
        taxi2.takeOff();
        System.out.println(taxi2);
    }
}
