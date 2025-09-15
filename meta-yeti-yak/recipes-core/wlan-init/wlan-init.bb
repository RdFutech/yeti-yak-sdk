SUMMARY = "Enable WLAN0 at boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://wlan-init.service"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/wlan-init.service ${D}${systemd_system_unitdir}
}

SYSTEMD_SERVICE:${PN} = "wlan-init.service"
inherit systemd
