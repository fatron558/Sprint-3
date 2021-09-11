package ru.sber.io

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile(filePath: String = "io/logfile.log", zipFile: String = "io/logfile.zip") {
        val zout = ZipOutputStream(FileOutputStream(zipFile))
        val fis = FileInputStream(filePath)
        val buffer: ByteArray
        val zipEntry = ZipEntry(filePath)

        fis.use {
            buffer = fis.readBytes()
        }

        zout.use {
            zout.putNextEntry(zipEntry)
            zout.write(buffer)
            zout.closeEntry()
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(unzippedFile: String = "io/logfileUnzip.log", zipFile: String = "io/logfile.zip") {
        val zis = ZipInputStream(FileInputStream(zipFile))
        val fout = FileOutputStream(unzippedFile)
        val buffer: ByteArray

        zis.use {
            zis.nextEntry
            buffer = zis.readBytes()
            zis.closeEntry()
        }

        fout.use {
            fout.write(buffer)
        }
    }
}