SUMMARY = "OpenVPN client config + certs + systemd unit"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit systemd

# Voeg je eigen bestanden toe
SRC_URI = " \
    file://ilucharge2.conf \
    file://openvpn-myclient.service \
"

S = "${WORKDIR}"

RDEPENDS:${PN} += "openvpn"

do_install() {
    # systemd service
    install -d ${D}${sysconfdir}/openvpn
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/openvpn-myclient.service ${D}${systemd_system_unitdir}/

    install -m 0644 ${WORKDIR}/ilucharge2.conf ${D}${sysconfdir}/openvpn/
}

FILES:${PN} += " \
  ${sysconfdir}/openvpn/* \
  ${systemd_system_unitdir}/openvpn-myclient.service \
"

SYSTEMD_SERVICE:${PN} = "openvpn-myclient.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

