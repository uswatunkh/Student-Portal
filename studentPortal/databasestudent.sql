-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 25, 2021 at 10:17 PM
-- Server version: 5.7.31
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `databasestudent`
--

-- --------------------------------------------------------

--
-- Table structure for table `bahasainternational`
--

DROP TABLE IF EXISTS `bahasainternational`;
CREATE TABLE IF NOT EXISTS `bahasainternational` (
  `idBahasa` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `periodeWisuda` varchar(20) NOT NULL,
  `tahunWisuda` varchar(10) NOT NULL,
  `namaBahasa` varchar(30) NOT NULL,
  `skor` int(11) NOT NULL,
  `tanggalTes` date NOT NULL,
  `scanBukti` text NOT NULL,
  `verifikasi` varchar(30) NOT NULL,
  PRIMARY KEY (`idBahasa`),
  KEY `Bahasa_FKIndex1` (`npm`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bahasainternational`
--

INSERT INTO `bahasainternational` (`idBahasa`, `npm`, `periodeWisuda`, `tahunWisuda`, `namaBahasa`, `skor`, `tanggalTes`, `scanBukti`, `verifikasi`) VALUES
(1, 12, '5455', '788', 'jhghj', 445, '2021-05-05', 'hbh', 'Sudah Diverifikasi');

-- --------------------------------------------------------

--
-- Table structure for table `daftarulg`
--

DROP TABLE IF EXISTS `daftarulg`;
CREATE TABLE IF NOT EXISTS `daftarulg` (
  `idDaftarUlang` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `idPeriode` int(10) UNSIGNED NOT NULL,
  `ukt` varchar(20) NOT NULL,
  `statusBayar` varchar(10) NOT NULL,
  `cetakKrs` text NOT NULL,
  `semester` tinyint(10) NOT NULL,
  `ip` float DEFAULT NULL,
  `ipk` float DEFAULT NULL,
  PRIMARY KEY (`idDaftarUlang`),
  KEY `npm` (`npm`),
  KEY `idPeriode` (`idPeriode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftarulg`
--

INSERT INTO `daftarulg` (`idDaftarUlang`, `npm`, `idPeriode`, `ukt`, `statusBayar`, `cetakKrs`, `semester`, `ip`, `ipk`) VALUES
(2, 12, 1, '3000.000', 'lunas', 'sas', 1, 34, 37);

-- --------------------------------------------------------

--
-- Table structure for table `datadosen`
--

DROP TABLE IF EXISTS `datadosen`;
CREATE TABLE IF NOT EXISTS `datadosen` (
  `idDosen` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idMataKuliah` int(10) UNSIGNED NOT NULL,
  `nip` int(20) NOT NULL,
  `namaDosen` varchar(50) NOT NULL,
  `nidn` int(20) NOT NULL,
  `noHp` int(15) NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `email` varchar(50) NOT NULL,
  `fotoDosen` text,
  PRIMARY KEY (`idDosen`),
  KEY `idMataKuliah` (`idMataKuliah`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `datadosen`
--

INSERT INTO `datadosen` (`idDosen`, `idMataKuliah`, `nip`, `namaDosen`, `nidn`, `noHp`, `alamat`, `email`, `fotoDosen`) VALUES
(2, 3, 56556, 'pak rahmat', 67567, 9987789, 'kjhf', 'kdlmfk', 'http://192.168.1.44/studentPortal/profile_image/12.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
CREATE TABLE IF NOT EXISTS `documents` (
  `sn` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `location` text NOT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `documents`
--

INSERT INTO `documents` (`sn`, `title`, `location`) VALUES
(10, 'myPDF', 'documents/myPDF.pdf'),
(9, 'myPDF', 'documents/myPDF.pdf'),
(8, 'myFile', 'documents/myFile.pdf'),
(7, 'myPDF', 'documents/myPDF.pdf'),
(11, 'myPDF', 'documents/myPDF.pdf'),
(12, 'myPDF', 'documents/myPDF.pdf'),
(13, 'myPDF', 'documents/myPDF.pdf'),
(14, 'myFile', 'documents/myFile.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `evaluasidosen`
--

DROP TABLE IF EXISTS `evaluasidosen`;
CREATE TABLE IF NOT EXISTS `evaluasidosen` (
  `idEvdos` int(30) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idDosen` int(11) UNSIGNED NOT NULL,
  `idKuisioner` int(50) UNSIGNED NOT NULL,
  `jawaban` varchar(200) NOT NULL,
  PRIMARY KEY (`idEvdos`),
  KEY `idDosen` (`idDosen`),
  KEY `idKuisioner` (`idKuisioner`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evaluasidosen`
--

INSERT INTO `evaluasidosen` (`idEvdos`, `idDosen`, `idKuisioner`, `jawaban`) VALUES
(1, 2, 16, '');

-- --------------------------------------------------------

--
-- Table structure for table `evaluasikuisioner`
--

DROP TABLE IF EXISTS `evaluasikuisioner`;
CREATE TABLE IF NOT EXISTS `evaluasikuisioner` (
  `idEvaluasi` int(30) NOT NULL AUTO_INCREMENT,
  `npm` int(11) UNSIGNED NOT NULL,
  `idDosen` int(30) UNSIGNED NOT NULL,
  `jawaban1_1` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idEvaluasi`),
  KEY `npm` (`npm`),
  KEY `idEvdos` (`idDosen`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evaluasikuisioner`
--

INSERT INTO `evaluasikuisioner` (`idEvaluasi`, `npm`, `idDosen`, `jawaban1_1`) VALUES
(1, 12, 2, 'pernah'),
(3, 12, 2, ''),
(4, 12, 2, 'Tidak Pernah');

-- --------------------------------------------------------

--
-- Table structure for table `hasilstd`
--

DROP TABLE IF EXISTS `hasilstd`;
CREATE TABLE IF NOT EXISTS `hasilstd` (
  `idHasilStudi` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idDaftarUlang` int(11) UNSIGNED NOT NULL,
  `idMataKuliah` int(10) UNSIGNED NOT NULL,
  `npm` int(10) UNSIGNED NOT NULL,
  `nilaiAngka` int(10) NOT NULL,
  `nilaiHuruf` tinytext NOT NULL,
  `totalNilai` int(5) NOT NULL,
  PRIMARY KEY (`idHasilStudi`),
  KEY `idPeriode` (`idDaftarUlang`),
  KEY `idMataKuliah` (`idMataKuliah`),
  KEY `npm` (`npm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jadwalkuliah`
--

DROP TABLE IF EXISTS `jadwalkuliah`;
CREATE TABLE IF NOT EXISTS `jadwalkuliah` (
  `idJadwal` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `idDosen` int(11) UNSIGNED NOT NULL,
  `idRuang` int(11) UNSIGNED NOT NULL,
  `idMataKuliah` int(10) UNSIGNED NOT NULL,
  `jamke` varchar(10) NOT NULL DEFAULT '',
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat','Sabtu') DEFAULT NULL,
  PRIMARY KEY (`idJadwal`),
  KEY `idDosen` (`idDosen`),
  KEY `idMataKuliah` (`idMataKuliah`),
  KEY `idRuang` (`idRuang`),
  KEY `npm` (`npm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `keterampilan`
--

DROP TABLE IF EXISTS `keterampilan`;
CREATE TABLE IF NOT EXISTS `keterampilan` (
  `idKeterampilan` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `namaKeterampilan` varchar(30) NOT NULL DEFAULT '',
  `jenis` varchar(50) NOT NULL DEFAULT '',
  `tingkat` varchar(25) NOT NULL DEFAULT '',
  `verifikasi` varchar(30) DEFAULT NULL,
  `scanBukti` text,
  PRIMARY KEY (`idKeterampilan`),
  KEY `Keterampilan_FKIndex1` (`npm`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keterampilan`
--

INSERT INTO `keterampilan` (`idKeterampilan`, `npm`, `namaKeterampilan`, `jenis`, `tingkat`, `verifikasi`, `scanBukti`) VALUES
(1, 12, 'ncjch', 'Teknis/Akademis (Hardskill)', 'jchc', 'Belum Diverifikasi', 'documents/12.ncjch.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `kuisioner`
--

DROP TABLE IF EXISTS `kuisioner`;
CREATE TABLE IF NOT EXISTS `kuisioner` (
  `idKuisioner` int(50) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pertanyaan` varchar(300) NOT NULL,
  `kategori` enum('Sikap Dosen saat Proses Perkuliah','Kelengkapan dan penguasaan Materi Dosen','Sikap Dosen saat memberikan Tugas dan Portofolio','Kesan','Pesan dan Saran') NOT NULL,
  PRIMARY KEY (`idKuisioner`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kuisioner`
--

INSERT INTO `kuisioner` (`idKuisioner`, `pertanyaan`, `kategori`) VALUES
(15, 'qqqqqqqqqqq', 'Sikap Dosen saat Proses Perkuliah'),
(16, 'eeeeeeeeeeeeee', 'Kelengkapan dan penguasaan Materi Dosen'),
(17, 'ooooooooooooooo', 'Sikap Dosen saat memberikan Tugas dan Portofolio'),
(18, '', 'Kesan');

-- --------------------------------------------------------

--
-- Table structure for table `magang`
--

DROP TABLE IF EXISTS `magang`;
CREATE TABLE IF NOT EXISTS `magang` (
  `idMagang` int(11) NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `judul` varchar(300) NOT NULL DEFAULT '',
  `tempat` varchar(50) NOT NULL DEFAULT '',
  `provinsi` varchar(30) NOT NULL DEFAULT '',
  `kota` varchar(30) NOT NULL DEFAULT '',
  `tanggalmulaiMagang` date NOT NULL DEFAULT '1999-01-01',
  `tanggalselesaiMagang` date NOT NULL DEFAULT '1999-01-01',
  `ringkasan` varchar(300) NOT NULL DEFAULT '',
  `scanBukti` text,
  `uploadLaporan` text,
  `verifikasi` varchar(30) NOT NULL,
  PRIMARY KEY (`idMagang`),
  KEY `Magang_FKIndex1` (`npm`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magang`
--

INSERT INTO `magang` (`idMagang`, `npm`, `judul`, `tempat`, `provinsi`, `kota`, `tanggalmulaiMagang`, `tanggalselesaiMagang`, `ringkasan`, `scanBukti`, `uploadLaporan`, `verifikasi`) VALUES
(1, 12, 'jvajvaja', 'ugusvuvs', 'hschschs', 'hschsc', '2021-05-12', '2021-05-12', 'ajvvaja', 'scanBukti_Magang/12.jvajvaja.pdf', 'laporan_Magang/12.jvajvaja.pdf', 'Belum Diverifikasi');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

DROP TABLE IF EXISTS `mahasiswa`;
CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `npm` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idProdi` int(10) UNSIGNED NOT NULL,
  `passwordUser` text,
  `statusDiri` enum('Aktif','Tidak Aktif') NOT NULL DEFAULT 'Aktif',
  `nik` varchar(15) NOT NULL DEFAULT '',
  `namaLengkap` varchar(80) NOT NULL DEFAULT '',
  `tempatLahir` varchar(30) NOT NULL DEFAULT '',
  `tanggalLahir` varchar(10) NOT NULL DEFAULT '',
  `jenisKelamin` varchar(10) NOT NULL DEFAULT '',
  `agama` varchar(20) NOT NULL DEFAULT '',
  `provinsiAsal` varchar(30) NOT NULL DEFAULT '',
  `kotaAsal` varchar(30) NOT NULL DEFAULT '',
  `kecamatanAsal` varchar(30) NOT NULL DEFAULT '',
  `desa` varchar(30) NOT NULL DEFAULT '',
  `alamatAsal` varchar(50) NOT NULL DEFAULT '',
  `alamatSekarang` varchar(50) NOT NULL DEFAULT '',
  `tahunAngkatan` varchar(15) NOT NULL DEFAULT '',
  `noHp` varchar(15) NOT NULL DEFAULT '',
  `email` varchar(30) NOT NULL DEFAULT '',
  `scanKtp` text,
  `imageProfil` text,
  PRIMARY KEY (`npm`),
  KEY `idProdi` (`idProdi`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`npm`, `idProdi`, `passwordUser`, `statusDiri`, `nik`, `namaLengkap`, `tempatLahir`, `tanggalLahir`, `jenisKelamin`, `agama`, `provinsiAsal`, `kotaAsal`, `kecamatanAsal`, `desa`, `alamatAsal`, `alamatSekarang`, `tahunAngkatan`, `noHp`, `email`, `scanKtp`, `imageProfil`) VALUES
(11, 1, '$2y$10$Q/BUldYRH1GpR6ehA/JqbeoAIoEd5evlGXsQctwYnG.cU7iz0NVi6', 'Aktif', '', '', '', '2021-5-6', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL),
(12, 1, '$2y$10$D.zCUKxIshd9UUqkwl0meetGC.O3ysCPu6Ffhl0jeXAK8pfCsPkcu', 'Aktif', '', '', '', '2021-5-6', '', '', '', '', '', '', '', '', '', '', 'khasanahnganjuk1122@gmail.com', 'http://192.168.1.44/studentPortal/ktp_image/12.jpeg', 'http://192.168.1.44/studentPortal/profile_image/12.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `matakuliah`
--

DROP TABLE IF EXISTS `matakuliah`;
CREATE TABLE IF NOT EXISTS `matakuliah` (
  `idMataKuliah` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idProdi` int(10) UNSIGNED NOT NULL,
  `namaMK` varchar(50) DEFAULT NULL,
  `deskripsi` varchar(255) DEFAULT NULL,
  `sks` int(10) UNSIGNED DEFAULT NULL,
  `jam` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idMataKuliah`),
  KEY `MataKuliah_FKIndex1` (`idProdi`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matakuliah`
--

INSERT INTO `matakuliah` (`idMataKuliah`, `idProdi`, `namaMK`, `deskripsi`, `sks`, `jam`) VALUES
(1, 1, 'Pemrogaman Web', 'kjdkj', 1, '12'),
(3, 1, 'Pemrogamanann', 'uyguyg', 5, '12');

-- --------------------------------------------------------

--
-- Table structure for table `orangtua`
--

DROP TABLE IF EXISTS `orangtua`;
CREATE TABLE IF NOT EXISTS `orangtua` (
  `idOrangTua` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `nikAyah` varchar(20) NOT NULL DEFAULT '',
  `namaAyah` varchar(30) NOT NULL DEFAULT '',
  `tglLahirAyah` date NOT NULL DEFAULT '1999-01-01',
  `pendidikanAyah` varchar(50) NOT NULL DEFAULT '',
  `pekerjaanAyah` varchar(50) NOT NULL DEFAULT '',
  `nipAyah` varchar(20) NOT NULL DEFAULT '',
  `pangkatAyah` varchar(50) NOT NULL DEFAULT '',
  `penghasilanAyah` varchar(50) NOT NULL DEFAULT '',
  `instansiAyah` varchar(25) NOT NULL DEFAULT '',
  `nikIbu` varchar(20) NOT NULL DEFAULT '',
  `namaIbu` varchar(30) NOT NULL DEFAULT '',
  `tglLahirIbu` date NOT NULL DEFAULT '1999-01-01',
  `pendidikanIbu` varchar(50) NOT NULL DEFAULT '',
  `pekerjaanIbu` varchar(20) NOT NULL DEFAULT '',
  `nipIbu` varchar(20) NOT NULL DEFAULT '',
  `pangkatIbu` varchar(50) NOT NULL DEFAULT '',
  `penghasilanIbu` varchar(50) NOT NULL DEFAULT '',
  `instansiIbu` varchar(25) NOT NULL DEFAULT '',
  `nikWali` varchar(20) NOT NULL DEFAULT '',
  `namaWali` varchar(30) NOT NULL DEFAULT '',
  `tglLahirWali` date NOT NULL DEFAULT '1999-01-01',
  `pendidikanWali` varchar(50) NOT NULL DEFAULT '',
  `pekerjaanWali` varchar(20) NOT NULL DEFAULT '',
  `nipWali` varchar(20) NOT NULL DEFAULT '',
  `pangkatWali` varchar(50) NOT NULL DEFAULT '',
  `penghasilanWali` varchar(50) NOT NULL DEFAULT '',
  `instansiWali` varchar(25) NOT NULL DEFAULT '',
  PRIMARY KEY (`idOrangTua`),
  KEY `OrangTua_FKIndex1` (`npm`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orangtua`
--

INSERT INTO `orangtua` (`idOrangTua`, `npm`, `nikAyah`, `namaAyah`, `tglLahirAyah`, `pendidikanAyah`, `pekerjaanAyah`, `nipAyah`, `pangkatAyah`, `penghasilanAyah`, `instansiAyah`, `nikIbu`, `namaIbu`, `tglLahirIbu`, `pendidikanIbu`, `pekerjaanIbu`, `nipIbu`, `pangkatIbu`, `penghasilanIbu`, `instansiIbu`, `nikWali`, `namaWali`, `tglLahirWali`, `pendidikanWali`, `pekerjaanWali`, `nipWali`, `pangkatWali`, `penghasilanWali`, `instansiWali`) VALUES
(44, 11, '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', ''),
(45, 12, '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `organisasi`
--

DROP TABLE IF EXISTS `organisasi`;
CREATE TABLE IF NOT EXISTS `organisasi` (
  `idOrganisasi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `namaOrganisasi` varchar(30) NOT NULL DEFAULT '',
  `tempat` varchar(30) DEFAULT NULL,
  `tahunMasuk` int(10) UNSIGNED DEFAULT NULL,
  `tahunKeluar` int(10) UNSIGNED DEFAULT NULL,
  `jabatan` varchar(30) DEFAULT NULL,
  `scanBukti` text,
  `verifikasi` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`idOrganisasi`),
  KEY `Organisasi_FKIndex1` (`npm`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organisasi`
--

INSERT INTO `organisasi` (`idOrganisasi`, `npm`, `namaOrganisasi`, `tempat`, `tahunMasuk`, `tahunKeluar`, `jabatan`, `scanBukti`, `verifikasi`) VALUES
(1, 12, 'jgufufhch', 'hfhfh', 255, 2556, 'hchch', 'scanBukti_Organisasi/12.jgufufhch.pdf', 'Belum Diverifikasi');

-- --------------------------------------------------------

--
-- Table structure for table `pengisiankuisioner`
--

DROP TABLE IF EXISTS `pengisiankuisioner`;
CREATE TABLE IF NOT EXISTS `pengisiankuisioner` (
  `idPengisian` int(50) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `idEvdos` int(30) UNSIGNED NOT NULL,
  `jawaban` varchar(300) NOT NULL,
  PRIMARY KEY (`idPengisian`),
  KEY `npm` (`npm`),
  KEY `idEvdos` (`idEvdos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengumuman`
--

DROP TABLE IF EXISTS `pengumuman`;
CREATE TABLE IF NOT EXISTS `pengumuman` (
  `idPengumuman` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` enum('INFORMASI','PERINGATAN','KUISIONER','DOWNLOAD') DEFAULT NULL,
  `body` varchar(100) NOT NULL DEFAULT '',
  `tanggalPengumuman` date DEFAULT NULL,
  PRIMARY KEY (`idPengumuman`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengumuman`
--

INSERT INTO `pengumuman` (`idPengumuman`, `title`, `body`, `tanggalPengumuman`) VALUES
(15, 'INFORMASI', ' Pengisian Kuesioner Evaluasi Mengajar Dosen Semester Genap TA 2020-2021', '2021-05-24'),
(16, 'PERINGATAN', 'ttttt', '2021-05-25'),
(17, 'KUISIONER', 'isiiiii', '2021-05-25'),
(18, 'DOWNLOAD', 'download', '2021-05-25');

-- --------------------------------------------------------

--
-- Table structure for table `periode`
--

DROP TABLE IF EXISTS `periode`;
CREATE TABLE IF NOT EXISTS `periode` (
  `idPeriode` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `periode` varchar(15) DEFAULT NULL,
  `tanggalMulaiPeriode` date NOT NULL,
  `tanggalSelesaiPeriode` date NOT NULL,
  PRIMARY KEY (`idPeriode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `periode`
--

INSERT INTO `periode` (`idPeriode`, `periode`, `tanggalMulaiPeriode`, `tanggalSelesaiPeriode`) VALUES
(1, '2017/2018 gasal', '2021-05-04', '2021-05-18');

-- --------------------------------------------------------

--
-- Table structure for table `presensi`
--

DROP TABLE IF EXISTS `presensi`;
CREATE TABLE IF NOT EXISTS `presensi` (
  `idPresensi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `idJadwal` int(11) UNSIGNED NOT NULL,
  `idDaftarUlang` int(11) UNSIGNED NOT NULL,
  `mingguKe` varchar(10) NOT NULL,
  `tanggalPresensi` date NOT NULL,
  `ket` enum('Hadir','Izin','Sakit','Alpha','Kosong') DEFAULT NULL,
  PRIMARY KEY (`idPresensi`),
  KEY `Presensi_FKIndex2` (`npm`),
  KEY `idJadwal` (`idJadwal`),
  KEY `idDaftarUlang` (`idDaftarUlang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `prestasi`
--

DROP TABLE IF EXISTS `prestasi`;
CREATE TABLE IF NOT EXISTS `prestasi` (
  `idPrestasi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `npm` int(10) UNSIGNED NOT NULL,
  `namaLomba` varchar(60) NOT NULL DEFAULT '',
  `tahun` varchar(10) NOT NULL DEFAULT '',
  `juara` varchar(15) NOT NULL DEFAULT '',
  `tingkat` varchar(40) NOT NULL DEFAULT '',
  `jenis` varchar(40) DEFAULT NULL,
  `scanBukti` text,
  `verifikasi` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`idPrestasi`),
  KEY `Prestasi_FKIndex1` (`npm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `prodi`
--

DROP TABLE IF EXISTS `prodi`;
CREATE TABLE IF NOT EXISTS `prodi` (
  `idProdi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `namaProdi` varchar(30) DEFAULT NULL,
  `kelas` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`idProdi`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prodi`
--

INSERT INTO `prodi` (`idProdi`, `namaProdi`, `kelas`) VALUES
(1, '-', '-'),
(2, 'D3 Teknik Listrik', '6B');

-- --------------------------------------------------------

--
-- Table structure for table `ruang`
--

DROP TABLE IF EXISTS `ruang`;
CREATE TABLE IF NOT EXISTS `ruang` (
  `idRuang` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `namaRuang` varchar(30) DEFAULT NULL,
  `kapasitas` int(10) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`idRuang`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ruang`
--

INSERT INTO `ruang` (`idRuang`, `namaRuang`, `kapasitas`) VALUES
(1, 'Ruang  204', 24);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bahasainternational`
--
ALTER TABLE `bahasainternational`
  ADD CONSTRAINT `bahasainternational_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);

--
-- Constraints for table `daftarulg`
--
ALTER TABLE `daftarulg`
  ADD CONSTRAINT `daftarulg_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`),
  ADD CONSTRAINT `daftarulg_ibfk_2` FOREIGN KEY (`idPeriode`) REFERENCES `periode` (`idPeriode`);

--
-- Constraints for table `datadosen`
--
ALTER TABLE `datadosen`
  ADD CONSTRAINT `datadosen_ibfk_1` FOREIGN KEY (`idMataKuliah`) REFERENCES `matakuliah` (`idMataKuliah`);

--
-- Constraints for table `evaluasidosen`
--
ALTER TABLE `evaluasidosen`
  ADD CONSTRAINT `evaluasidosen_ibfk_2` FOREIGN KEY (`idDosen`) REFERENCES `datadosen` (`idDosen`),
  ADD CONSTRAINT `evaluasidosen_ibfk_3` FOREIGN KEY (`idKuisioner`) REFERENCES `kuisioner` (`idKuisioner`);

--
-- Constraints for table `evaluasikuisioner`
--
ALTER TABLE `evaluasikuisioner`
  ADD CONSTRAINT `evaluasikuisioner_ibfk_1` FOREIGN KEY (`idDosen`) REFERENCES `datadosen` (`idDosen`),
  ADD CONSTRAINT `evaluasikuisioner_ibfk_2` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);

--
-- Constraints for table `hasilstd`
--
ALTER TABLE `hasilstd`
  ADD CONSTRAINT `hasilstd_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`),
  ADD CONSTRAINT `hasilstd_ibfk_3` FOREIGN KEY (`idMataKuliah`) REFERENCES `matakuliah` (`idMataKuliah`),
  ADD CONSTRAINT `hasilstd_ibfk_4` FOREIGN KEY (`idDaftarUlang`) REFERENCES `daftarulg` (`idDaftarUlang`);

--
-- Constraints for table `jadwalkuliah`
--
ALTER TABLE `jadwalkuliah`
  ADD CONSTRAINT `jadwalkuliah_ibfk_2` FOREIGN KEY (`idRuang`) REFERENCES `ruang` (`idRuang`),
  ADD CONSTRAINT `jadwalkuliah_ibfk_3` FOREIGN KEY (`idMataKuliah`) REFERENCES `matakuliah` (`idMataKuliah`),
  ADD CONSTRAINT `jadwalkuliah_ibfk_5` FOREIGN KEY (`idDosen`) REFERENCES `datadosen` (`idDosen`),
  ADD CONSTRAINT `jadwalkuliah_ibfk_6` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);

--
-- Constraints for table `keterampilan`
--
ALTER TABLE `keterampilan`
  ADD CONSTRAINT `keterampilan_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);

--
-- Constraints for table `magang`
--
ALTER TABLE `magang`
  ADD CONSTRAINT `magang_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);

--
-- Constraints for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `mahasiswa_ibfk_1` FOREIGN KEY (`idProdi`) REFERENCES `prodi` (`idProdi`);

--
-- Constraints for table `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD CONSTRAINT `matakuliah_ibfk_1` FOREIGN KEY (`idProdi`) REFERENCES `prodi` (`idProdi`);

--
-- Constraints for table `orangtua`
--
ALTER TABLE `orangtua`
  ADD CONSTRAINT `orangtua_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);

--
-- Constraints for table `organisasi`
--
ALTER TABLE `organisasi`
  ADD CONSTRAINT `organisasi_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);

--
-- Constraints for table `pengisiankuisioner`
--
ALTER TABLE `pengisiankuisioner`
  ADD CONSTRAINT `pengisiankuisioner_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`),
  ADD CONSTRAINT `pengisiankuisioner_ibfk_2` FOREIGN KEY (`idEvdos`) REFERENCES `evaluasidosen` (`idEvdos`);

--
-- Constraints for table `presensi`
--
ALTER TABLE `presensi`
  ADD CONSTRAINT `presensi_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`),
  ADD CONSTRAINT `presensi_ibfk_2` FOREIGN KEY (`idJadwal`) REFERENCES `jadwalkuliah` (`idJadwal`),
  ADD CONSTRAINT `presensi_ibfk_3` FOREIGN KEY (`idDaftarUlang`) REFERENCES `daftarulg` (`idDaftarUlang`);

--
-- Constraints for table `prestasi`
--
ALTER TABLE `prestasi`
  ADD CONSTRAINT `prestasi_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
