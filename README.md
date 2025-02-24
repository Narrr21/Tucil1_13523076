# **IQ Puzzle Solver**

## **Deskripsi**
Program ini dikembangkan sebagai bagian dari Tugas Kecil 1 mata kuliah **IF2211 - Strategi Algoritma** pada Semester II tahun 2024/2025. Program ini bertujuan untuk menemukan satu solusi yang memungkinkan dalam menempatkan balok IQ Puzzle pada papan berukuran **M x N**.

## **Persyaratan**
Pastikan Java telah terinstal di perangkat Anda. Untuk memeriksa apakah Java sudah terpasang, jalankan perintah berikut di terminal atau command prompt:

```sh
java -version
```
Jika Java belum terinstal, silakan unduh dan instal Java dari situs resminya: [Oracle Java](https://www.oracle.com/java/) atau gunakan OpenJDK yang sesuai dengan sistem Anda.

## **Cara Menjalankan Program**

### **1. Masuk ke Direktori Proyek**
Buka terminal atau command prompt, lalu navigasikan ke folder **src** dalam proyek IQ Puzzle dengan perintah berikut:

```sh
cd IQ_Puzzle/src
```

### **2. Kompilasi Program**
Gunakan perintah berikut untuk mengompilasi semua file Java dalam proyek:

```sh
javac -d out $(find . -type f -name "*.java")
```

Perintah ini akan mengompilasi seluruh file sumber dan menyimpan hasilnya dalam folder `out`.

> **Catatan:** Untuk pengguna Windows, gunakan perintah berikut sebagai alternatif:
> 
> ```sh
> javac -d out (Get-ChildItem -Path . -Recurse -Filter "*.java").FullName
> ```

### **3. Menjalankan Program**
Setelah proses kompilasi selesai, jalankan program dengan perintah berikut:

```sh
java -cp out main.Main
```

## **Informasi Pembuat**
Program ini dikembangkan oleh:

**Nadhif Al Rozin**  
NRP: 13523076  
Mata Kuliah: IF2211 - Strategi Algoritma

---

Jika mengalami kendala saat menjalankan program, pastikan Java telah terinstal dengan benar dan periksa apakah direktori kerja sudah sesuai dengan petunjuk di atas.
