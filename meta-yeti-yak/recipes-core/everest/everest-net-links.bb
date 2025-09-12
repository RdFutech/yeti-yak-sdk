SUMMARY = "Systemd .link files to fix Ethernet names (eth0=bcmgenet, eth1=qcaspi)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://10-bcmgenet-eth0.link \
           file://10-qcaspi-eth1.link \
           file://98-tap-ignore.link \
           file://98-tap.network"

S = "${WORKDIR}"

inherit allarch

do_install() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/10-bcmgenet-eth0.link ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/10-qcaspi-eth1.link ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/98-tap-ignore.link ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/98-tap.network ${D}${sysconfdir}/systemd/network/
}

FILES:${PN} += "${sysconfdir}/systemd/network/10-bcmgenet-eth0.link \
                ${sysconfdir}/systemd/network/10-qcaspi-eth1.link \
                ${sysconfdir}/systemd/network/98-tap-ignore.link \
                ${sysconfdir}/systemd/network/98-tap.network"
