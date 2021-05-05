-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 05, 2021 at 07:05 PM
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
  `tahunWisuda` varchar(10) NOT NULL,
  `namaBahasa` varchar(30) NOT NULL,
  `skor` int(11) NOT NULL,
  `tanggalTes` date NOT NULL,
  `scanBukti` text NOT NULL,
  PRIMARY KEY (`idBahasa`),
  KEY `Bahasa_FKIndex1` (`npm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftarulg`
--

INSERT INTO `daftarulg` (`idDaftarUlang`, `npm`, `idPeriode`, `ukt`, `statusBayar`, `cetakKrs`, `semester`, `ip`, `ipk`) VALUES
(20, 110, 4, '8000.000', 'lunas', 'DFFSDF', 1, NULL, NULL);

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
  PRIMARY KEY (`idDosen`),
  KEY `idMataKuliah` (`idMataKuliah`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `datadosen`
--

INSERT INTO `datadosen` (`idDosen`, `idMataKuliah`, `nip`, `namaDosen`, `nidn`, `noHp`, `alamat`, `email`) VALUES
(6, 1, 56556, 'pak rahmat', 67567, 9987789, 'kjhzkjzj', 'uus@gmail.com'),
(7, 1, 9, 'pak loi', 67567, 9987789, 'jnj', 'nnn');

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evaluasidosen`
--

INSERT INTO `evaluasidosen` (`idEvdos`, `idDosen`, `idKuisioner`, `jawaban`) VALUES
(14, 7, 16, ''),
(15, 6, 16, ''),
(16, 7, 17, ''),
(17, 7, 15, '');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jadwalkuliah`
--

INSERT INTO `jadwalkuliah` (`idJadwal`, `npm`, `idDosen`, `idRuang`, `idMataKuliah`, `jamke`, `hari`) VALUES
(8, 110, 7, 1, 1, '2-6', 'Senin');

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
) ENGINE=InnoDB AUTO_INCREMENT=438 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keterampilan`
--

INSERT INTO `keterampilan` (`idKeterampilan`, `npm`, `namaKeterampilan`, `jenis`, `tingkat`, `verifikasi`, `scanBukti`) VALUES
(433, 110, '', '-Pilih Jenis Keterampilan-', '', 'Sudah Diverifikasi', 'documents/110..pdf'),
(436, 110, 'aaaa', 'Teknis/Akademis (Hardskill)', 'ddcf', 'Belum Diverifikasi', 'documents/110.aaaa.pdf'),
(437, 110, 'aaaa', 'Teknis/Akademis (Hardskill)', 'ddcf', 'Belum Diverifikasi', 'documents/110.aaaa.pdf');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magang`
--

INSERT INTO `magang` (`idMagang`, `npm`, `judul`, `tempat`, `provinsi`, `kota`, `tanggalmulaiMagang`, `tanggalselesaiMagang`, `ringkasan`, `scanBukti`, `uploadLaporan`, `verifikasi`) VALUES
(6, 110, 'aaaa', 'aaaa', 'aaaaa', 'aaaaaa', '2021-05-04', '2021-05-04', 'aaaaa', 'scanBukti_Magang/110.aaaa.pdf', 'laporan_Magang/110.aaaa.pdf', 'Belum Diverifikasi'),
(10, 110, 'cccc', 'cccc', 'cccc', 'cccc', '2021-05-05', '2021-05-05', 'ccc', 'scanBukti_Magang/110.cccc.pdf', 'laporan_Magang/110.cccc.pdf', 'Belum Diverifikasi');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

DROP TABLE IF EXISTS `mahasiswa`;
CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `npm` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`npm`)
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`npm`, `passwordUser`, `statusDiri`, `nik`, `namaLengkap`, `tempatLahir`, `tanggalLahir`, `jenisKelamin`, `agama`, `provinsiAsal`, `kotaAsal`, `kecamatanAsal`, `desa`, `alamatAsal`, `alamatSekarang`, `tahunAngkatan`, `noHp`, `email`, `scanKtp`, `imageProfil`) VALUES
(11, '$2y$10$A1DTXxYoYLhq1ZUtpoI43.hqkvXsHmXQUM0HeK0o6CEZ7P4LUR3a.', 'Aktif', '', '', '', '2021-4-19', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL),
(22, '$2y$10$8/a1dn4oYUuhqSNUlV37GeElmT17.GWv009rQ/pHAht64fzQe78Ye', 'Aktif', '', '', '', '2021-4-27', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL),
(33, '$2y$10$MP8VAE0fR7euwqk4lLAN3e2/HZlJvPSMGXPKWLv7j1kIsv6n335Hm', 'Aktif', '', '', '', '2021-4-21', 'Perempuan', '', '', '', '', '', '', '', '', '', '', 'http://192.168.1.43/studentPortal/ktp_image/33.jpeg', 'http://192.168.1.43/studentPortal/profile_image/33.jpeg'),
(66, '$2y$10$NIv1z7ppqe0Nw0sNmkZdzOl1iqpcsQZwv43X2E2RNDnMDlfWeB9gG', 'Aktif', '', '', '', '2021-4-15', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL),
(99, '$2y$10$O80rvqBHhfM1AKtq4ByycOfreO2/2f7AjsB3JHTaoMCofusRUoHIC', 'Aktif', '', '', '', '2021-4-15', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL),
(110, '$2y$10$tJRe/pzEKEZHaPqrfYEYTemRsarFt8ZGuK.5olO0HoIBZRWnNJFjC', 'Aktif', '', 'fhxfjxfxgjx', 'jvhch', '2021-4-9', '', '', '', 'gfychf', '', '', '', '', '', '', '', 'http://192.168.1.44/studentPortal/ktp_image/110.jpeg', 'http://192.168.1.44/studentPortal/profile_image/110.jpeg'),
(112, '$2y$10$Xi8FKcbsp4X6G.MmY/n4Ueky2tLKcb9mczrGiPGTZHtlZ.Mfqy1A6', 'Aktif', '', '', '', '2021-4-1', '', '', '', '', '', '', '', '', '', '', '', 'http://192.168.10.62/studentPortal/ktp_image/112.jpeg', 'http://192.168.10.62/studentPortal/profile_image/112.jpeg'),
(336, '$2y$10$kiEbYO2AgLGLLWWyT2.8peks1N1u4RX5LhdyJerQs2jkzhaZMwiN2', 'Aktif', '', '', '', '2021-4-8', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL),
(337, '$2y$10$5kx/1rMp6Lv8/Y3epWMydunMaoki.K0pmXfk2.wOHsv6grnhgQkUu', 'Aktif', '', '', '', '2021-4-1', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matakuliah`
--

INSERT INTO `matakuliah` (`idMataKuliah`, `idProdi`, `namaMK`, `deskripsi`, `sks`, `jam`) VALUES
(1, 1, 'Pemrogaman Web', 'peemrkdhskjs', 5, '12'),
(2, 1, 'Pemrogaman Android', 'jsxjnw', 23, '12');

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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orangtua`
--

INSERT INTO `orangtua` (`idOrangTua`, `npm`, `nikAyah`, `namaAyah`, `tglLahirAyah`, `pendidikanAyah`, `pekerjaanAyah`, `nipAyah`, `pangkatAyah`, `penghasilanAyah`, `instansiAyah`, `nikIbu`, `namaIbu`, `tglLahirIbu`, `pendidikanIbu`, `pekerjaanIbu`, `nipIbu`, `pangkatIbu`, `penghasilanIbu`, `instansiIbu`, `nikWali`, `namaWali`, `tglLahirWali`, `pendidikanWali`, `pekerjaanWali`, `nipWali`, `pangkatWali`, `penghasilanWali`, `instansiWali`) VALUES
(28, 33, '', '', '2021-04-15', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', ''),
(29, 22, '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', ''),
(30, 336, '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', ''),
(31, 11, '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', ''),
(32, 99, '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', ''),
(33, 66, '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', ''),
(35, 110, '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', ''),
(36, 112, 'rido', 'rido', '2021-04-08', 'ejbje', 'jevjvej', 'jsvjvsj', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '', '', '', '1999-01-01', '', '', '', '', '', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organisasi`
--

INSERT INTO `organisasi` (`idOrganisasi`, `npm`, `namaOrganisasi`, `tempat`, `tahunMasuk`, `tahunKeluar`, `jabatan`, `scanBukti`, `verifikasi`) VALUES
(1, 110, 'Hmjt', 'pnm', 2017, 2019, 'sosma', 'aa', 'Sudah Diverifikasi'),
(2, 110, 'uuuu', 'uuuu', 2018, 2069, 'gvgg', 'scanBukti_Organisasi/110.uuuu.pdf', 'Belum Diverifikasi');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengisiankuisioner`
--

INSERT INTO `pengisiankuisioner` (`idPengisian`, `npm`, `idEvdos`, `jawaban`) VALUES
(1, 110, 17, ''),
(9, 110, 15, 'pernah');

-- --------------------------------------------------------

--
-- Table structure for table `pengumuman`
--

DROP TABLE IF EXISTS `pengumuman`;
CREATE TABLE IF NOT EXISTS `pengumuman` (
  `idPengumuman` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `isiPengumuman` varchar(100) DEFAULT NULL,
  `tanggalPengumuman` date DEFAULT NULL,
  PRIMARY KEY (`idPengumuman`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengumuman`
--

INSERT INTO `pengumuman` (`idPengumuman`, `isiPengumuman`, `tanggalPengumuman`) VALUES
(11, 'ggh', '2021-04-01'),
(12, 'hjhgh', '2021-04-02');

-- --------------------------------------------------------

--
-- Table structure for table `periode`
--

DROP TABLE IF EXISTS `periode`;
CREATE TABLE IF NOT EXISTS `periode` (
  `idPeriode` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `periode` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idPeriode`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `periode`
--

INSERT INTO `periode` (`idPeriode`, `periode`) VALUES
(4, '2018/2019 Gasal'),
(5, '2018/2019 Genap');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `presensi`
--

INSERT INTO `presensi` (`idPresensi`, `npm`, `idJadwal`, `idDaftarUlang`, `mingguKe`, `tanggalPresensi`, `ket`) VALUES
(4, 110, 8, 20, '1', '2021-04-06', 'Hadir');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prestasi`
--

INSERT INTO `prestasi` (`idPrestasi`, `npm`, `namaLomba`, `tahun`, `juara`, `tingkat`, `jenis`, `scanBukti`, `verifikasi`) VALUES
(5, 110, 'bbbbb', 'bbbb', 'bbbb', 'Nasional', 'Individu', 'scanBukti_Prestasi/110.bbbbb.pdf', 'Belum Diverifikasi');

-- --------------------------------------------------------

--
-- Table structure for table `prodi`
--

DROP TABLE IF EXISTS `prodi`;
CREATE TABLE IF NOT EXISTS `prodi` (
  `idProdi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `namaProdi` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idProdi`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prodi`
--

INSERT INTO `prodi` (`idProdi`, `namaProdi`) VALUES
(1, 'D3 Teknologi Informasi');

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
