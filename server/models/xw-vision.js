
module.exports = function(SwaggerApi) {

/**
 * Find quadrilateral context hulls surrounding both a ticket and the letter grid inside the ticket.  The ticket location can be computed by this operation or provided by caller.
 * @param {any} undefined undefined
 * @param {any} undefined undefined
 * @param {any} undefined undefined
 * @callback {Function} callback Callback function
 * @param {Error|string} err Error object
 * @param {any} result Result object
 */
SwaggerApi.putUsersUserGuidTicketProjectsXwProjGuid = function(userGuid, xwProjGuid, contentOverride, callback) {
  // Replace the code below with your implementation.
  // Please make sure the callback is invoked.
  process.nextTick(function() {
    var err = new Error('Not implemented');
    callback(err);
  });

  /*
  var err0 = new Error('');
  err0.statusCode = 500;
  return cb(err0);
  */
}

/**
 * Find quadrilateral context hulls surrounding both a ticket and the letter grid inside the ticket.  The ticket location can be computed by this operation or provided by caller.
 * @param {any} undefined undefined
 * @param {any} undefined undefined
 * @callback {Function} callback Callback function
 * @param {Error|string} err Error object
 * @param {ticketIdDescription} result Result object
 */
SwaggerApi.getUsersUserGuidTicketProjectsXwProjGuidTicketId = function(userGuid, xwProjGuid, callback) {
  // Replace the code below with your implementation.
  // Please make sure the callback is invoked.
  process.nextTick(function() {
    var err = new Error('Not implemented');
    callback(err);
  });

}



SwaggerApi.remoteMethod('putUsersUserGuidTicketProjectsXwProjGuid',
  { isStatic: true,
  accepts:
   [ { arg: 'userGuid',
       type: 'string',
       description: '',
       required: true,
       http: { source: 'path' } },
     { arg: 'xwProjGuid',
       type: 'string',
       description: '',
       required: true,
       http: { source: 'path' } },
     { arg: 'body',
       type: 'any',
       description: '',
       required: true,
       http: { source:'body' } } ],
  returns: [],
  http:
   { verb: 'put',
     path: '/users/:userGuid/ticketProjects/:xwProjGuid' },
  description: 'Find quadrilateral context hulls surrounding both a ticket and the letter grid inside the ticket.  The ticket location can be computed by this operation or provided by caller.' }
);

SwaggerApi.remoteMethod('getUsersUserGuidTicketProjectsXwProjGuidTicketId',
  { isStatic: true,
  accepts:
   [ { arg: undefined,
       type: 'any',
       description: undefined,
       required: undefined,
       http: { source: undefined } },
     { arg: undefined,
       type: 'any',
       description: undefined,
       required: undefined,
       http: { source: undefined } } ],
  returns:
   [ { description: 'A request for the analysis has been accepted and the result will be returned as a payload posted to the URL given by the \'X-PortfolioReplyTo\' input header',
       type: 'ticketIdDescription',
       arg: 'data',
       root: true } ],
  http:
   { verb: 'get',
     path: '/users/:userGuid/ticketProjects/:xwProjGuid/ticketId' },
  description: 'Find quadrilateral context hulls surrounding both a ticket and the letter grid inside the ticket.  The ticket location can be computed by this operation or provided by caller.' }
);

}
