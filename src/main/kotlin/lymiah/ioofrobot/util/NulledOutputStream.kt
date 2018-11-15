package lymiah.ioofrobot.util

import java.io.OutputStream


class NulledOutputStream : OutputStream() {
    override fun write(b: Int) {
    }
}