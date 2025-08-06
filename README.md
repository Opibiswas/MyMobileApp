Rice Leaf Disease Detection
Built in Java (Android Studio)

An Android app to detect rice leaf diseases by analyzing leaf images using machine learning

🚀 Overview
Built with Java in Android Studio

Uses a lightweight CNN model (converted via TensorFlow Lite or OpenCV)

Supports detection of common rice diseases:

Brown Spot

Leaf Blast

Leaf Smut / Blight
(e.g. based on Indonesian/Indonesian‑class datasets; typical four‑class model) 
Academia.edu
+15
IIETA
+15
GitHub
+15
Jurnal Politeknik Ganesha Medan
+3
Medium
+3
ResearchGate
+3
Stack Overflow

Designed for offline inference on device, suitable for rural deployment 
IIETA
ResearchGate

🎯 Features
Capture or select leaf images from gallery or camera

Preprocessing pipeline: resizing, normalization, segmentation (color thresholds or OpenCV)

TFLite/OpenCV model inference in Java

Disease classification with probability and label

Guidance: brief descriptions of disease symptoms & treatment suggestions

Optionally: progress tracking and history logs 
Scribd
GitHub

📁 Project Structure
pgsql
Copy
Edit
/app
  └─ src/
      └─ main/
          ├─ java/your/package/ModelRunner.java  ← TFLite/OpenCV loader and predictor
          ├─ java/your/package/MainActivity.java  ← UI
          └─ assets/
              ├─ model.tflite or model.pb
              └─ classes.json    ← maps index → disease name
res/
  └─ layout/, drawable/, values/
models/
  └─ training‑notebook.ipynb  ← Python/Colab model training script
dataset/
  ├─ images/
  └─ classes.json
README.md
LICENSE
