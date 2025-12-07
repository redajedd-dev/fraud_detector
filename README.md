#  Détection de Fraude Bancaire

Un moteur de détection de fraude basé sur le Machine Learning, capable d'analyser des transactions financières en temps réel via une API REST.

##  Fonctionnalités
* **Analyse Temps Réel :** API REST recevant des transactions JSON.
* **Machine Learning :** Utilise l'algorithme **J48 (Decision Tree)** via la librairie **Weka**.
* **Données Réelles :** Entraîné sur un extrait du dataset *Credit Card Fraud Detection* (Kaggle).
* **Filtrage Intelligent :** Sélection automatique des features pertinentes (V1, V2, V3, Amount).

##  Stack Technique
* **Langage :** Java 17
* **Framework :** Spring Boot 3 (Web)
* **IA/ML :** Weka 3.8
* **Build :** Maven

##  Comment lancer le projet

<img width="1440" height="900" alt="Screenshot 2025-12-07 at 15 12 02" src="https://github.com/user-attachments/assets/dab87c1e-139f-4589-86eb-ba4c08ba783a" />


1. **Cloner le repo :**
   ```bash
   git clone https://github.com/redajedd-dev/fraud_detector.git
