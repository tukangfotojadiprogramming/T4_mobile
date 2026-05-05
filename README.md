# T4_mobile
Nama: ZAMZAMI SATRIA TEGAR 
NIM: F1D02320029

Aplikasi Student Contact App merupakan sebuah aplikasi berbasis Android yang dirancang untuk memfasilitasi penyimpanan serta pengelolaan data kontak mahasiswa secara efektif dan efisien. Aplikasi ini memiliki antarmuka yang sederhana, intuitif, dan user-friendly, aplikasi ini bertujuan untuk mempermudah pengguna dalam mengelola informasi kontak mahasiswa tanpa memerlukan keterampilan teknis yang kompleks.
Aplikasi ini memiliki fitur yakni Halaman Login, Halaman Daftar Siswa, Halaman Catatan Siswa, Student Directory, Perubahan Data jadi sistem informasi, Halaman Edit Data informatika, Dialog Hapus Data, Data Berhasil Dihapus, Tambah Siswa, Data Berhasil Ditambahkan dan Halaman Profile

<img width="738" height="1600" alt="WhatsApp Image 2026-05-05 at 11 53 46" src="https://github.com/user-attachments/assets/b7a6c75f-105d-4288-ad44-af459edb5db8" />
<img width="1080" height="2340" alt="WhatsApp Image 2026-05-05 at 11 53 46 (1)" src="https://github.com/user-attachments/assets/af35e2b6-dc4c-45b2-a493-576f3d6e7565" />
<img width="1080" height="2340" alt="WhatsApp Image 2026-05-05 at 11 53 45" src="https://github.com/user-attachments/assets/ab2878a4-114e-4366-ae8b-e3c2dc01cbb7" />
<img width="1080" height="2340" alt="WhatsApp Image 2026-05-05 at 11 53 45 (2)" src="https://github.com/user-attachments/assets/1855a577-bea7-4186-8932-9666180d2492" />
<img width="1080" height="2340" alt="WhatsApp Image 2026-05-05 at 11 53 45 (1)" src="https://github.com/user-attachments/assets/5611133a-7370-492a-ab11-e4168c3f5010" />
<img width="738" height="1600" alt="WhatsApp Image 2026-05-05 at 11 53 44" src="https://github.com/user-attachments/assets/67ce2e0e-1b34-452f-a370-5890634eecd0" />
<img width="1080" height="2340" alt="WhatsApp Image 2026-05-05 at 11 53 44 (1)" src="https://github.com/user-attachments/assets/f3ce39cb-955d-47f8-9dac-6ec85b1f27fd" />
<img width="1080" height="2340" alt="WhatsApp Image 2026-05-05 at 11 53 43" src="https://github.com/user-attachments/assets/434531e0-2fab-4209-85d3-90567d081bbd" />
<img width="738" height="1600" alt="WhatsApp Image 2026-05-05 at 11 53 43 (2)" src="https://github.com/user-attachments/assets/682207a0-ac13-4ca9-a24e-ad629d24e359" />
<img width="738" height="1600" alt="WhatsApp Image 2026-05-05 at 11 53 43 (1)" src="https://github.com/user-attachments/assets/6f01992d-f8f6-44b6-9df6-f2a375ed8022" />
<img width="1080" height="2340" alt="WhatsApp Image 2026-05-05 at 11 53 47" src="https://github.com/user-attachments/assets/c03e3271-1d69-4c8b-a951-59bc99e6e97e" />


**Databese**
Aplikasi ini menggunakan SQLite Database sebagai metode utama dalam penyimpanan data. SQLite merupakan sistem manajemen basis data relasional yang bersifat ringan (lightweight) dan terintegrasi langsung pada perangkat Android, sehingga sangat sesuai untuk kebutuhan aplikasi mobile. Pemilihan SQLite didasarkan pada beberapa pertimbangan utama. Pertama, SQLite tidak memerlukan koneksi internet dalam proses operasionalnya, sehingga aplikasi tetap dapat digunakan secara optimal dalam kondisi offline. Kedua, ukurannya yang ringan menjadikannya tidak membebani kinerja perangkat, sehingga cocok diterapkan pada berbagai spesifikasi smartphone. Ketiga, implementasi SQLite di Android Studio relatif mudah karena telah didukung secara native oleh sistem Android. Selain itu, data yang disimpan bersifat lokal di dalam perangkat pengguna, sehingga akses terhadap data menjadi lebih cepat dan responsif.

**Kendala dan Solusi**

Kendala utama terjadi saat proses menampilkan data yang terhubung dengan database, di mana data tidak dapat ditampilkan dengan baik pada antarmuka aplikasi. Permasalahan ini disebabkan oleh ketidaksesuaian pada adapter serta kesalahan dalam proses data binding. Untuk mengatasi hal tersebut, dilakukan perbaikan pada struktur adapter serta memastikan bahwa proses binding data dari database ke tampilan telah sesuai. Selain itu, proses debugging dilakukan dengan memanfaatkan fitur Logcat pada Android Studio guna mengidentifikasi sumber error secara lebih spesifik.

Kendala lain muncul pada tahap awal pengembangan, yaitu penggunaan kapt (Kotlin Annotation Processing Tool) yang menyebabkan terjadinya konflik atau ketidaksesuaian dependensi. Hal ini berdampak pada proses build aplikasi yang tidak berjalan dengan semestinya. Solusi yang diterapkan adalah dengan mengganti penggunaan kapt menjadi ksp (Kotlin Symbol Processing), yang lebih ringan, cepat, serta memiliki kompatibilitas yang lebih baik terhadap dependensi yang digunakan.

Selain itu, kendala juga terjadi pada proses version control, yaitu ketidakmampuan dalam melakukan commit ke repositori. Permasalahan ini umumnya disebabkan oleh konfigurasi Git yang belum tepat atau belum adanya perubahan (staged changes) yang siap untuk dikomit. Solusi yang dilakukan adalah dengan memastikan konfigurasi Git telah benar, menambahkan file ke staging area menggunakan perintah yang sesuai, serta memastikan tidak terdapat konflik pada repository sebelum melakukan proses commit.
