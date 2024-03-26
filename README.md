# state-machines

## Goals
- Event driven
- Determine allowed transitions
- Actions followed by state-change events
- In(source/entry) and Out(target/exit) actions per target state
- Guards
- Track the current state of machines
- Consistent state within the same machine

## Order State Diagram
```mermaid
stateDiagram-v2
READY --> PLACED: PLACE_ORDER
note right of PLACED: Order is placed
PLACED --> PAID: PAY
note right of PAID: Payment is made
PAID --> SHIPPED: SHIP
note right of SHIPPED: Order is shipped
SHIPPED --> DELIVERED: DELIVER
note right of DELIVERED: Order is delivered
SHIPPED --> PENDING: CANCEL
DELIVERED --> PENDING: CANCEL
note right of PENDING: Waiting for return
PENDING --> CANCELLED: RETURN
PAID --> CANCELLED: CANCEL
note right of CANCELLED: Order is cancelled
```
