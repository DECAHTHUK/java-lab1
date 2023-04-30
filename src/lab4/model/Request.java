package lab4.model;

import java.util.Objects;

public class Request {
    private int callingFloor;

    private Direction direction;

    public Request() {
    }

    public Request(int callingFloor, Direction direction) {
        this.callingFloor = callingFloor;
        this.direction = direction;
    }

    public int getCallingFloor() {
        return callingFloor;
    }

    public void setCallingFloor(int callingFloor) {
        this.callingFloor = callingFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return callingFloor == request.callingFloor && direction == request.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(callingFloor, direction);
    }
}
