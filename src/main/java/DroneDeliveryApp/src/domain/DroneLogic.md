# Drone Logic Documentation

## Overview

The Drone class represents an autonomous delivery drone with specific attributes and capabilities for package delivery
operations. Each drone has a unique identification, payload capacity, battery management, and location tracking
functionality.

## Drone States and Status

- **DroneStatus Types:**
    - IDLE: Default state, ready for new assignments
    - CHARGING: Drone is charging its battery
    - MAINTENANCE: Drone is under maintenance
    - IN_TRANSIT: Drone is actively delivering a package

## Validation Rules

### General Constraints

- Drone ID must be unique and non-null
- Maximum payload must be non-negative
- Battery level must be between 0-100%
- Mileage must be non-negative
- Current location coordinates must be valid
- Drone status must be defined

### Operational Rules

- A drone carrying a package must have an assigned customer
- A drone in maintenance cannot have an assigned customer
- A drone must be at the base to:
    - Load packages
    - Start deliveries
    - Charge battery
    - Undergo maintenance

## Delivery Process

1. **Pre-delivery Checks:**
    - Drone must be IDLE
    - Drone must be at base location
    - Package weight must not exceed max payload
    - Drone must be empty

2. **Delivery Sequence:**
    - Load package at base
    - Transit to customer location
    - Unload package at destination
    - Return to base
    - Reset status to IDLE

## Operation Requirements

### Package Loading

- Must be at base location
- Must be in IDLE status
- Must be empty
- Package weight must not exceed max payload

### Package Unloading

- Must be at customer's destination
- Must be IN_TRANSIT status
- Must have a package loaded

### Battery Management

- Charging only available at base location
- Battery level monitored during operations

### Maintenance

- Only performed at base location
- Cannot have assigned deliveries during maintenance


