# Coupang Logistics Flow Simulation

A discrete-event / agent-based simulation comparing the efficiency of
different Logistics Center–Distribution Center matching strategies in an
e-commerce fulfillment network, modeled on Coupang's delivery system in
South Korea.

> Originally developed as a Modeling & Simulation term project.

## Overview

The simulation models the order lifecycle in an e-commerce logistics
network, from manufacturing/fulfillment all the way to last-mile delivery:

```
Order placed → Check stock
            → (if insufficient) wait for restock
            → Pack & ship to Logistics Center
            → Wait for scheduled shipment
            → Deliver to customer
```

The core research question: **does the strategy used to match
DistributionCenters (customers) to LogisticsCenters affect overall delivery
efficiency?** Four candidate matching strategies are implemented and
benchmarked against each other (see `MatchingAlgorithms.java` for details):

1. Nearest-only (pure nearest-by-route)
2. Greedy stable matching (capacity-constrained, cost-minimizing)
3. Nearest with a uniformity constraint (forced even load)
4. Random, uniform assignment (capacity-capped)

## Repository Structure

```
.
├── main model.alp              # Full AnyLogic model (agents, GUI, GIS, animation)
├── Main.java                   # Top-level model logic (extracted, see note below)
├── MatchingAlgorithms.java     # The 4 benchmarked L-D matching strategies
├── FulfillmentCenter.java
├── LogisticsCenter.java
├── DistributionCenter.java
├── Vehicle.java
├── LocalVehicle.java
├── plotting.r                  # Plots simulation output across strategies
├── KruskalWallis and Dunn.r    # Statistical comparison across strategies
└── README.md
```

## Running the Model

The full simulation (`main model.alp`) requires
[AnyLogic](https://www.anylogic.com/) (Personal Learning Edition is free):

1. Open `main model.alp` in AnyLogic.
2. Run the model from the `Main` experiment.
3. To benchmark a different matching strategy, edit which method is called
   in `Main.ResetCenters()` — see `MatchingAlgorithms.java` for the four
   available strategies.

> **Note on the `.java` files:** these are a readable, version-controlled
> snapshot of the model's user-written logic only — not a standalone,
> compilable Java project. AnyLogic auto-generates all surrounding
> scaffolding (agent classes, GUI bindings, GIS space, etc.), which isn't
> reproduced here. Each file is annotated with which AnyLogic class it was
> extracted from.

## Analysis

Simulation output across the four matching strategies was compared
statistically:

- **`plotting.r`** — visualizes simulation output across strategies
- **`KruskalWallis and Dunn.r`** — Kruskal-Wallis test (overall difference
  across strategies) followed by Dunn's test (pairwise post-hoc comparison)

```bash
Rscript plotting.r
Rscript "KruskalWallis and Dunn.r"
```

## Background

Motivated by the scale of Korea's online shopping market and recurring
customer complaints about delivery reliability, this project simulates
Coupang's fulfillment → logistics → last-mile delivery pipeline to evaluate
how the center-matching strategy affects overall logistics efficiency.
