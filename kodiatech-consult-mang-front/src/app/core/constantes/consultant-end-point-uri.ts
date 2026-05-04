export class ConsultantEndPointURI {
  private static BASE = '/api/v1/';

  static AUTH = {
    LOGIN: ConsultantEndPointURI.BASE + 'auth/authenticate',
    REFRESH: ConsultantEndPointURI.BASE + 'auth/refreshtoken'
  };

  static CONSULTANT = {
    BASE: ConsultantEndPointURI.BASE + 'consultant/'
  };

  static ADMIN = {
    ADD_CONSULTANT: ConsultantEndPointURI.BASE + 'admin/add/consultant',
    CONSULTANTS: ConsultantEndPointURI.BASE + 'admin/consultants/',
    GET_CONSULTANT_BY_ID: (id: number | string) =>  `${ConsultantEndPointURI.BASE}admin/consultant/${id}`
  };

}
