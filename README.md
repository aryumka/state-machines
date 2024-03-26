# state-machines

## Goals
- Event driven
- Determine allowed transitions
- Actions followed by state-change events
- In(source/entry) and Out(target/exit) actions per state
- Guards
- Track the current state of machines
- Consistent state within the same machine

## Order State Diagram
```mermaid
stateDiagram-v2
READY --> PLACED: PLACE_ORDER
PLACED --> PAID: PAY
PAID --> CANCELLED: CANCEL
PAID --> SHIPPED: SHIP
SHIPPED --> DELIVERED: DELIVER
SHIPPED --> PENDING: CANCEL
DELIVERED --> PENDING: CANCEL
PENDING --> CANCELLED: RETURN
```
