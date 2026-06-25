# 2024-Logistic-Flow-In-an-E-commerce-Platform
Hybrid modeling simulation comparing logistics center-distribution matching strategies, modeled on Coupang's fulfillment network.

# Logistic Flow in an E-commerce Platform — Coupang Case Study

## Overview
A simulation study of logistics flow within Coupang's e-commerce fulfillment and delivery network, motivated by Korea's 228-trillion-won online shopping market and persistent customer complaints about delivery reliability.

## Topic
Simulate the flow of logistics in an e-commerce platform (Coupang), comparing the efficiency of logistics systems under different matching methods between Logistics Centers and Distribution Centers (customers).

## Modeling Approach
Three candidate simulation paradigms were considered for the Fulfillment → Logistics (Delivery) pipeline:
- Discrete event model
- Agent-based model
- Hybrid simulation model

The simulation plan models the order lifecycle: stock check → wait for restock → pack & ship to logistics → wait for shipment timing → deliver to customer.

## Analysis
- Statistical comparison of center-matching strategies using the **Kruskal-Wallis test** and **Dunn's test** (post-hoc).
- Simulation implemented in Java (`ResetCenters` and related classes); results plotted and tested in R.

## Files
- `Logistic Flow In an E-commerce Platform.pdf` — full presentation deck, including motivation, modeling concept, simulation plan, results, and an appendix with full Java/R source code listings (slides 27+).

## Notes
The Java and R source code currently exists only as screenshots embedded in the appendix slides (slides 33–38). If you want a clean, runnable code repo, that source would need to be retyped from the slide images.
