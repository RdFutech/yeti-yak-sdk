SUMMARY = "Stable serial symlinks and tmpfiles for Everest"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://90-everest-serial.rules \
           file://everest-tmpfiles.conf"

S = "${WORKDIR}"

inherit allarch systemd

do_install() {
    # udev rule
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/90-everest-serial.rules ${D}${sysconfdir}/udev/rules.d/

    # systemd-tmpfiles (to create /dev/everest at boot)
    install -d ${D}${libdir}/tmpfiles.d
    install -m 0644 ${WORKDIR}/everest-tmpfiles.conf ${D}${libdir}/tmpfiles.d/
}

# Ensure systemd-tmpfiles runs
RDEPENDS:${PN} += "systemd"
FILES:${PN} += "${sysconfdir}/udev/rules.d/90-everest-serial.rules \
                ${libdir}/tmpfiles.d/everest-tmpfiles.conf"
