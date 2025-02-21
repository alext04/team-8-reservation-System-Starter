package flight.reservation.plane;

import flight.reservation.plane.Helicopter;
import flight.reservation.plane.PassengerDrone;
import flight.reservation.plane.PassengerPlane;
import flight.reservation.plane.Aircraft;


public class AircraftFactory {
    public static Aircraft createAircraft(String type, String model) {
        switch (type.toLowerCase()) {
            case "helicopter":
                return new Helicopter(model);
            case "passengerdrone":
                return new PassengerDrone(model);
            case "passengerplane":
                return new PassengerPlane(model);
            default:
                throw new IllegalArgumentException(String.format("Aircraft type '%s' is not recognized", type));
        }
    }
}