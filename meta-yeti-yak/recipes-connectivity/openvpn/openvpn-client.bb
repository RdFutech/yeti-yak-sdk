SUMMARY = "OpenVPN client config + certs + systemd unit"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit systemd

# Voeg je eigen bestanden toe
SRC_URI = " \
    file://ilucharge2.conf \
    file://openvpn-futech.service \
"

S = "${WORKDIR}"

RDEPENDS:${PN} += "openvpn"

do_install() {
    install -d ${D}${sysconfdir}/openvpn/client
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/openvpn-futech.service ${D}${systemd_system_unitdir}/
    install -m 0644 ${WORKDIR}/ilucharge2.conf ${D}${sysconfdir}/openvpn/client/
}

FILES:${PN} += " \
  ${sysconfdir}/openvpn/client/* \
  ${systemd_system_unitdir}/openvpn-futech.service \
"

SYSTEMD_SERVICE:${PN} = "openvpn-futech.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

