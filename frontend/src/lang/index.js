/* ---------------------------------------------------------- */
/* LEVELS HELP ENG */
/* ---------------------------------------------------------- */
const LEVELS_DOWN_VERB_EN = 'This will <b>reduce</b> the depth of subsections that are aggregated in this view by one level less.<br><br>'
const LEVELS_UP_VERB_EN = 'This will <b>increase</b> the depth of subsections that are aggregated in this view by one level more.<br><br>'
const LEVELS_ALL_VERB_EN = 'This will aggregate the cards from <b>all subsections</b> (of all levels of subsections) under this section.<br><br>'

const LEVELS_BASE_EN = 'Selected subsections are highlighted in blue in the navigation panel on the left.<br><br>- When levels is 1, you will only see the cards in this section. <br><br>- When levels is 2, you will see the cards of this section, followed by the cards of all its immediate subsections. <br><br>- When levels is 3, you will see the cards of the subsections of the subsections too. <br><br>- ... and so on ...'

const LEVELS_DOWN_EN = LEVELS_DOWN_VERB_EN + LEVELS_BASE_EN
const LEVELS_UP_EN = LEVELS_UP_VERB_EN + LEVELS_BASE_EN
const LEVELS_ALL_EN = LEVELS_ALL_VERB_EN + LEVELS_BASE_EN

/* ---------------------------------------------------------- */
/* LEVELS HELP ES */
/* ---------------------------------------------------------- */
const LEVELS_DOWN_VERB_ES = '<b>Reduce</b> (en 1) el nivel de profundidad de subsecciones cuyas tarjetas son mostradas en esta vista.<br><br>'

const LEVELS_UP_VERB_ES = '<b>Aumenta</b> (en 1) el nivel de profundidad de subsecciones cuyas tarjetas son mostradas en esta vista.<br><br>'

const LEVELS_ALL_VERB_ES = 'Muestra todas las tarjetas, <b>inluyendo las de todas las subsecciones</b>, de una sección en todos los niveles de profunidada.<br><br>'

const LEVELS_BASE_ES = 'Las secciones seleccionadas cuyas tarjetas son visibles son realzadas en azul en el panel de navegación de la izquierda.<br><br>- Cuando el nivel es 1, solamente se verán las tarjetas propias de esa sección. . <br><br>- Cuando el nivel es 2, verás las tarjetas de esta sección, seguidas de las tarjetas de las subsecciones, en su respectivo orden.<br><br>- Cuando el nivel es 3, verás las tarjetas de las subsecciones de las subsecciones de esta sección. <br><br>- ... y así sucesivamente ...'

const LEVELS_DOWN_ES = LEVELS_DOWN_VERB_ES + LEVELS_BASE_ES
const LEVELS_UP_ES = LEVELS_UP_VERB_ES + LEVELS_BASE_ES
const LEVELS_ALL_ES = LEVELS_ALL_VERB_ES + LEVELS_BASE_ES

/* ---------------------------------------------------------- */
/* SCOPES HELP ENG */
/* ---------------------------------------------------------- */

const SCOPE_COMMON_EN = '<br><br>- "PRIVATE": only the user who adds the element to that section can see it.<br><br>- "SHARED": all the members of that initiative ecosystem (all parent initiatives and their subinitiatives) can see it  (but only the user who added the card can remove, move or edit it)<br><br>- "COMMON": Visible to all initiative ecosystem members, and if the initiative is public, visible to non-members too. Only EDITORS or ADMINS of the initiative can edit COMMON cards.<br><br>- <b>Note:</b> The same element can have different scopes on different sections.'

const SCOPE_CARDS_BASE_EN = 'A card can be added in a section with different scopes:' + SCOPE_COMMON_EN

const SCOPE_SUBSECTIONS_BASE_EN = 'A subsection can be added in a section with different scopes:' + SCOPE_COMMON_EN

/* ---------------------------------------------------------- */
/* SCOPES HELP ES */
/* ---------------------------------------------------------- */

const SCOPE_COMMON_ES = '<br><br>- "PRIVADA": Visible únicamente por el usuario que añade el elemento en esa sección y editable únicamente por su autor (que puede ser otro usuario).<br><br>- "COMPARTIDA": Visible para todos los miembros de la iniciativa, pero sólamente editable por el autor<br><br>- "COMÚN": Visible para todos los miembros de la iniciativa, y, si la iniciativa es pública, visible para cualquier visitante. Únicamente los EDITORES o ADMINS de una iniciativa pueden editar secciones o tarjetas COMUNES.<br><br>- <b>Nota:</b> El mismo elemento (tarjeta o sección) puede tener diferente configuración de visiblidad en diferentes secciones.'

const SCOPE_CARDS_BASE_ES = 'Una tarjeta puede ser añadida en una sección con diferentes configuraciones de visiblidad:' + SCOPE_COMMON_ES

const SCOPE_SUBSECTIONS_BASE_ES = 'Una sección puede ser añadida como subsección de otra sección con diferentes configuraciones de visiblidad:' + SCOPE_COMMON_ES

/* ---------------------------------------------------------- */
/* DRAG AND DROP HELP ENG */
/* ---------------------------------------------------------- */

const ENABLE_DD_CARDS_EN = 'This will enable/disable Drag and Drop of cards, allowing you to<br><br>- Reorder cards in this section.<br><br>- Moving cards to other sections by dropping them over an existing section on the navigation panel on the left.<br><br>- Copying (adding) cards to another section by dropping them on an existing section on the navigation panel on the left while holding the CTRL key.<br><br><b>Note:</b> PRIVATE and SHARED cards can only be placed <i>after</i> a COMMON card, they cannot be placed <i>before</i> one.'

const ENABLE_DD_SECTIONS_EN = 'This will enable/disable Drag and Drop of sections, allowing you to<br><br>- Rorder subsections in a given section.<br><br>- Moving subsections to other sections by dropping them over an existing section (dropping them in the left half of the target section will add the dragged section at the <i>same level</i>, while dropping it on the right half will add it <i>as a subsection</i>).<br><br>- Copying (adding) subsections to another section by dropping them on an existing section while holding the CTRL key (same rules apply as for moving a section).<br><br><b>Note:</b> PRIVATE and SHARED sections can only be placed <i>after</i> a COMMON section, they cannot be placed <i>before</i> one.'

/* ---------------------------------------------------------- */
/* DRAG AND DROP HELP ES */
/* ---------------------------------------------------------- */

const ENABLE_DD_CARDS_ES = 'Activar o desactivar la posibilidad de arrastrar tarjetas de una sección a otra permitiendo: <br><br>- Reordenar las tarjetas dentro de una misma sección. <br><br>- Mover una tarjeta de una sección soltándola sobre otra sección del panel de navegación de secciones de la izquierda.<br><br>- Copiar una tarjeta en otra sección soltándola mientras se mantiene presionada la tecla Ctrl del teclado.<br><br><b>Nota:</b> Tarjetas PRIVADAS y COMPARTIDAS solo pueden ser ubicadas después de una tarjeta común y no <i>antes</i> de ella.'

const ENABLE_DD_SECTIONS_ES = 'Activar o desactivar la posibilidad de arrastrar secciones de una sección a otra permitiendo: <br><br>- Reordenar las subsecciones dentro de una misma sección. <br><br>- Mover una subsección de una sección soltándola sobre otra sección del panel de navegación de secciones de la izquierda (si se suelta en la mitad izquierda del título de la sección, se añadirá al mismo nivel y después de ella, mientras que si se suelta en la mitad derecha de la sección se añadirá como nueva última subsección de ella). <br><br>- Copiar una tarjeta en otra sección soltándola mientras se mantiene presionada la tecla Ctrl del teclado.<br><br><b>Nota:</b> Tarjetas PRIVADAS y COMPARTIDAS solo pueden ser ubicadas después de una tarjeta común y no <i>antes</i> de ella.'

/* ---------------------------------------------------------- */
/* TRANSLATIONS */
/* ---------------------------------------------------------- */
const translations = {
  en: {
    general: {
      'SHOW': 'show',
      'HIDE': 'hide',
      'ENABLE': 'enable',
      'DISABLE': 'disable',
      'LOGIN_SIGNUP': 'LOG IN / SIGNUP',
      'NO_RESULTS_FOUND': 'no results found',
      'CREATE_NEW': 'create new',
      'NAME': 'Name',
      'DESCRIPTION': 'Description',
      'REQUIRED': 'required',
      'FIELD_CANNOT_BE_EMPTY': 'field cannot be empty',
      'FIELD_TOO_LONG': 'field too long',
      'FIELD_ONLY_LETTERS': 'field can only contain letters, numbers and/or spaces',
      'OK': 'ok',
      'YES': 'yes',
      'NO': 'no',
      'ACCEPT': 'Accept',
      'CANCEL': 'Cancel',
      'CONFIRM': 'Confirm',
      'ADD': 'add',
      'INHERIT': 'Inherit',
      'CUSTOM': 'Custom',
      'EDIT': 'edit',
      'DELETE': 'delete',
      'REMOVE': 'remove',
      'CREATE': 'Create',
      'SAVE': 'Save',
      'NOTIFICATIONS': 'notifications',
      'CHANGE': 'change',
      'UPLOAD_IMAGE': 'upload image',
      'NOTES': 'Notes',
      'CONFIGURATION': 'Configuration',
      'SHOW_MORE': 'show more',
      'MORE': 'more',
      'EMPTY': 'empty',
      'HOME': 'home',
      'PROFILE': 'profile',
      'LOGOUT': 'logout',
      'PROFILE_CC': 'Profile',
      'SETTINGS': 'Settings',
      'LANGUAGE': 'Language'
    },
    landing: {
      'PRINCIPLES': 'PRINCIPLES',
      'FEATURES': 'FEATURES',
      'DEMOS': 'DEMOS',
      'PARTICIPATE': 'PARTICIPATE',
      'WELCOME': 'WELCOME TO',
      'BRIEF': 'CollectiveOne is a method (and a platform) to develop open, decentralized and collaborative initiatives: initiatives to which anyone can, potentially, contribute, and which are collectively owned and self-governed by their contributors.  <br><br>Oh, and just by chance, it is an amazing collaboration tool for "normal" teams too ;)',
      'EXPLORE': 'EXPLORE THE APP',
      'OPEN_COLLAB': 'OPEN COLLABORATION',
      'OPEN_COLLAB_CONTENT': 'Like open-source projects, entry barriers are kept low to allow anyone to contribute to an initiative in similar conditions as those of previous contributors.',
      'CONTRIB_REC': 'CONTRIBUTIONS RECORD AND VALUE',
      'CONTRIB_REC_CONTENT': 'Contributions are recognized and valued relative to each other using project-specific tokens.',
      'LIQUID_OWNERSHIP': 'LIQUID OWNERSHIP',
      'LIQUID_OWNERSHIP_CONTENT': 'The ownership of each project is linked to contributions, and, therefore, to tokens. The more you contribute, the more you should own.',
      'DIST_GOV': 'DISTRIBUTED GOVERNANCE',
      'DIST_GOV_CONTENT': 'Configurable governance at each level of an initiative enables decisions to be taken openly and collectively.',
      'TRANSP': 'TRANSPARENCY',
      'TRANSP_CONTENTS': 'The information of an initiative can be kept public as a mean to attract and enable people to contribute.',
      'VALUE_ACC': 'VALUE ACCOUNTING',
      'VALUE_ACC_CONTENT': 'Basic infrastructure for managing tokens and transferring them to subinitiatives and users.',
      'COLL_VISION': 'COLLECTIVE VISION',
      'COLL_VISION_CONTENT': 'A dedicated module that enables initiatives to collectively influence and agree on the initiatives vision and plans.',
      'TASK_MAN': 'TASK MANAGEMENT',
      'TASK_MAN_CONTENT': 'Kanban board to organize the tasks of an initiative and value these using the initiatives tokens.',
      'DISTR_GOB': 'DISTRIBUTED GOVERNANCE',
      'DISTR_GOB_CONTENT': 'A dedicated module that enables initiatives to take decisions collectively and efficiently.',
      'FLEXIBLE_CHANNELS': 'Flexibly Interconnected Channels',
      'FLEXIBLE_CHANNELS_CONTENTS': 'Collaboration is all about effective communication. In CollectiveOne channels can be flexibly nested and interconnected to better represent the real structure of your organization.',
      'PERSONALIZED_CHANNELS': 'Personal Perspectives',
      'PERSONALIZED_CHANNELS_CONTENTS': 'Each person is a world of their own. In ColllectiveOne each user can arrange the channels the way they want.',
      'CHANNEL_CONTENTS': 'Cards',
      'CHANNEL_CONTENTS_CONTENTS': 'Beside messages, channel can store cards (content that is not lost in the timeline), useful for storing relevant information, annoucements, proposals or agreements.',
      'PARTICIPATE_TEXT': 'CollectiveOne is an open project itself and is being developed using <b><a href="http://www.collectiveone.org/#/app/inits/ac119496-5e3e-1db5-815e-3f192a890001">CollectiveOne itself</a></b>. Contributions are welcome! <br><br>Signup to the platform and then visit <a href="http://www.collectiveone.org/#/app/inits/ac119496-5e3e-1db5-815e-3f192a890001/people">CollectiveOne\'s members page</a> to get involved.',
      'FOLLOW_US': 'FOLLOW US!'
    },
    initiatives: {
      'INITIATIVE': 'initiative',
      'MY_INITIATIVES': 'My Initiatives',
      'PUBLIC_INITIATIVES': 'Public Initiatives',
      'MY_INITS_EMPTY': 'you are not yet part of any initiative',
      'NEW_SUBINITIATIVE': 'new subinitiative',
      'CREATE_ONE': 'create one',
      'NEW': 'new initiative',
      'CURRENT_INITIATIVE': 'Current Initiative',
      'TAG': 'Tag',
      'TAGS': 'Tags',
      'FILTER_BY_TAG': 'Filter by tag',
      'SELECT_TAG': 'select tag',
      'NEW_INIT_MODAL': 'New Initiative',
      'CREATE_NEW_TOKEN_Q': 'Create a new token?',
      'ONE_ADMIN_ATLEAST': 'there must be at least one admin',
      'INITIAL_MEMBERS': 'Initial Members',
      'NEW_SUBINITIATIVE_OF': 'New subinitiative of',
      'EDIT_INITIATIVE': 'Edit Initiative',
      'COLOR': 'Color',
      'VISIBILITY': 'Visibility',
      'PRIVATE': 'private',
      'ECOSYSTEM': 'ecosystem',
      'INHERIT': 'inherit',
      'PUBLIC': 'public',
      'DELETE_INIT': 'Delete Initiative',
      'CONFIRM_DELETE_MSG': '<b>Warning:</b> This will delete this initiative and all its subinitiatives and, if there is a parent initiative, transfer all its assets back to it. Please confirm.',
      'DELETE_WARNING': '<b>Warning:</b> Are you sure you want to completely delete the initiative "{name}"? This will delete all its contents.',
      'SUBINITIATIVES': 'SUBINITIATIVES'
    },
    members: {
      'WANT_TO_CONTRIB_Q': 'Want to contribute?',
      'WANT_TO_CONTRIB_MSG': 'The administrators of this initiative will be notified of your interest and will be able to add you as a contributor to this initiative.',
      'ADMINS_NOTIFIED': 'The admins of this initiative have been notified.',
      'USER_ADDED_SUCCESS': 'User succesfully added.',
      'ERROR_NOTIFYING': ' Error notifying the admins',
      'MEMBERS_OF': 'Members of {title}',
      'MEMBERS_OF_SUBS': 'Members of subinitiatives of {title}',
      'ADD_USER': 'add user',
      'CHOOSE_ROLE': 'choose role',
      'ADMIN': 'ADMIN',
      'EDITOR': 'EDITOR',
      'MEMBER': 'MEMBER',
      'AVATAR': 'AVATAR',
      'NICKNAME': 'NICKNAME',
      'ROLE': 'ROLE',
      'DELETE': 'DELETE',
      'LEAVE': 'LEAVE'
    },
    tokens: {
      'NUMBER_OF_TOKENS': 'Number of Tokens',
      'TOKEN_NAME': 'Token Name',
      'TOKENS': 'Tokens',
      'TRANSFERS_HISTORY_FROM': 'Transfers history from {initName}',
      'TRANSFERS_HISTORY_FROM_SUBINITIATIVES_OF': 'Transfers history from subinitiatives of {initName}',
      'TRANSFER_ASSETS_Q': 'Transfer assets?',
      'NO_ASSETS_TRANSFERRED_CONF': 'warning: you have not selected any assets. Please confirm this is ok below, or specify the amount to be transferred',
      'NO_ASSETS_TRANSFERRED_MSG': 'You will not transfer assets from {initName} to this new initiative. Please confirm this is ok.',
      'TRANSFER_FROM': 'Transfer from {initName}',
      'TOKEN_TO_TRANSFER': 'Token to transfer',
      'NO_ASSETS_IN_PARENT': 'the parent initiative does not hold any tokens',
      'OF_EXISTING': 'of existing',
      'STILL_AVAILABLE': 'Still Available',
      'UNDER_PENDING_PR': 'Under pending peer-review transfers',
      'TRANSFERRED_TO_SUBS': 'Transferred to sub-initiatives',
      'TRANSFERRED_TO_MEMS': 'Transferred to members',
      'AMOUNT_TO_TRANSF': 'Amount to be transfered',
      'AVAILABLE': 'available',
      'NO_ASSETS_HELD': 'No tokens are currently held by this initiative',
      'TRANSFER_TO_INITIATIVE': 'Transfer tokens to other initiative',
      'TRANSFER_TO': 'Transfer to ',
      'ZERO_TOKENS_ERROR': 'Please select how many tokens to transfer',
      'MOTIVE': 'Motive',
      'NO_PARENTS_ERROR': '{name} does not have parent or child initiatives',
      'TRANSFER_TO_USERS': 'Transfer tokens to users',
      'PEER_REVIEW_TITLE': 'Peer-reviewed transfer to users',
      'DIRECT_TITLE': 'Direct transfer to users',
      'DIRECT': 'Direct',
      'PEER_REVIEWED': 'Peer Reviewed',
      'NOT_ENOUGH_RECEIVERS': 'please add at least one receiver',
      'NOT_ENOUGH_EVALS': 'please add at least one evaluator',
      'NOT_ALL_DONORS': 'not all receivers can be donors',
      'PERCENTAGES_WRONG': 'percentages must sum 100%',
      'NO_ASSETS_SELECTED': 'warning: you have not selected any assets. Please confirm this is ok below, or specify the amount to be transferred',
      'INITIAL_STATE': 'Initial State',
      'ON_HOLD': 'on-hold',
      'OPEN': 'open',
      'MAX_DURATION': 'Max duration',
      'DAYS': 'day | days',
      'SELF_BIAS_VISIBLE': 'Self-bias visible',
      'ALL_EVALS_VISIBLE': 'All evaluations visible',
      'NO_ASSETS_TRASFERRED': 'You will not transfer assets to this users. Please confirm this is ok.',
      'NO_MEMBERS_SELECTED': 'no members have been selected',
      'ONE_RECEIVER': 'one receiver only',
      'MULTIPLE_RECEIVERS': 'add multiple receivers',
      'RECEIVERS': 'Receivers',
      'DONOR_WARNING': '<b>Warning:</b> {{ donorUsersStr }} will be part of the evaluation but the tokens they receive will be distributed among the rest of receivers!',
      'EVALUATORS': 'Evaluators',
      'SAME_AS_RECEIVERS': 'same as receivers',
      'DIFF_FROM_RECEIVERS_Q': 'different from receivers?',
      'DATE_UC': 'DATE',
      'FROM_UC': 'FROM',
      'VALUE_UC': 'VALUE',
      'TO_UC': 'TO',
      'MOTIVE_UC': 'MOTIVE',
      'STATUS_UC': 'STATUS',
      'TYPE_UC': 'TYPE',
      'FROM_NAME': 'from <b>{name}</b>',
      'REVERT_TX': 'revert transaction',
      'REVERT_WARNING': '<b>Warning:</b> This will order the transfer of tokens from the receivers back to the initiative. Please confirm you would like to revert this transaction.',
      'DELETE_WARNING': '<b>Warning:</b> This will delete this assignation. All evaluations made will be lost and no tokens will be transferred. Please confirm.',
      'OPEN_START_EVALS': 'open/start evaluations',
      'OPEN_WARNING': '<b>Warning:</b> This will open the peer-review process and evaluators will have {days} days to do provide their evaluations.',
      'REVERT_APPROVE_WARNING': '<b>Attention:</b> One of the admins would like to revert this transation. Do you approve this revert?',
      'REJECT': 'Reject',
      'APPROVE': 'Approve',
      'APPROVED_MSG': 'approved!',
      'PEER_REV_STATUS': '{nEvalsPending} evaluations pending closes in {timeRemaining}',
      'PEER_REV_TIP': '<b>Tip:</b> fill the values with numbers that make sense relative to each other, <b>even if they dont sum 100%</b>, and then click the "autoscale" button bellow.',
      'MY_EVAL': 'My evaluation',
      'USER_UC': 'USER',
      'DONOR_UC': 'DONOR',
      'MINE_UC': 'MINE',
      'PRE_DONOR_UC': 'PRE-DONOR',
      'FINAL_UC': 'FINAL',
      'FINAL_TOKENS_UC': 'FINAL TOKENS',
      'TOTAL_UC': 'TOTAL',
      'KNOW_DONT_UC': 'KNOW/DON\'T',
      'SELF_BIAS_UC': 'SELF-BIAS',
      'REMOVE_UC': 'REMOVE',
      'DONT_KNOW': ' don\'t know',
      'DONT_KNOW_SHORT': 'DK',
      'TOTAL_ASSIGNED': 'total assigned',
      'AUTO_ROUNDED': 'auto-rounded',
      'AUTOSCALE': 'autoscale',
      'CHANGE_MY_EVAL': 'Change my evaluation',
      'RESULTS': 'Results',
      'ALL_EVALS': 'All Evaluations'
    },
    activity: {
      'EDIT_NOTIFICATIONS_OF': 'Edit notifications for the {title} {type}',
      'EDIT_GLOBAL_NOTIFICATIONS': 'Edit your global notification preferences',
      'GENERAL_CONF': 'General configuration',
      'INHERITING_FROM': 'Inhereting notification preferences from: <br><b>{name}</b>',
      'DISABLE': 'disable',
      'ONLY_MENTIONS': 'only mentions',
      'ONLY_MESSAGES': 'only messages',
      'ALL_EVENTS': 'all events',
      'IN_APP_NOTIFICATIONS': 'In-App Notifications',
      'PUSH_NOTIFICATIONS_MSG': 'Push Notifications:<br><small>Sent only when not active in the app</small>',
      'IMMEDIATE_MAIL_MSG': 'Immediate Email:<br><small>Sent only when not active in the app</small>',
      'EMAIL_SUMMARY_MSG': 'Email Summary:<br><small>Sent only when not already read in app</small>',
      'DAILY': 'daily',
      'WEEKLY': 'weekly',
      'TITLE_AND_TYPE': '"{title}" {type}',
      'GLOBAL_NOTIFICATIONS_PREFS': 'your global notification preferences'
    },
    model: {
      'SECTION': 'section',
      'NEW_SECTION': 'New Section',
      'MODEL_SECTION': 'Model Section',
      'AS_TOP_LEVEL_IN': 'As top level section under the initiative',
      'IN_SECTION': 'In Section',
      'CREATE_NEW': 'Create New',
      'ADD_EXISTING': 'Add Existing',
      'SCOPE_IN': 'Scope (in "{title}" section)',
      'NEW_SCOPE_IN': 'New scope (in "{title}" section)',
      'PRIVATE': 'private',
      'SHARED': 'shared',
      'COMMON': 'common',
      'PRIVATE_MSG': 'Private (only I can see it)',
      'SHARED_MSG': 'Shared (only members can see it but only I can edit it)',
      'COMMON_MSG': 'Common (public if initiative is public and only members can edit it)',
      'TITLE': 'Title',
      'CONTENT': 'Content',
      'CLOSE_WARNING': 'You are currently editing this section. Are you sure you want to close it? Any changes would get lost.',
      'SEARCH_AND_SELECT_SECTION': 'search and select a section',
      'DELETE_CARD_WARNING': '<b>Warning:</b> This will delete the card from all the sections in which it is used.',
      'REMOVE_CARD_WARNING': '<b>Warning:</b> This will remove this card from the {title} section.',
      'ADD_SUBSECTION': 'Add Subsection',
      'REMOVE_SECTION_WARNING': '<b>Warning:</b> Are you sure you want to remove the section "{sectionTitle}" as a subsection of "{inSectionTitle}"?',
      'DELETE_SECTION_WARNING': '<b>Warning:</b> Are you sure you want to completely delete the section "{sectionTitle}"? This will delete it from all the sections in which it is a subsection.'
    },
    help: {
      'MESSAGES-TAB-TT': 'messages',
      'MESSAGES-TAB-DET': 'Here you will find a list of all messages and events that were sent or occurred in this section. The list will also include the messages and events of all the subsections',

      'CARDS-SUMMARY-TAB-TT': 'cards summary',
      'CARDS-SUMMARY-TAB-DET': 'Here you will find a <b>condensed</b> list of all the cards in this section (and its subsections if selected levels is greater than 2)',

      'CARDS-TAB-TT': 'cards',
      'CARDS-TAB-DET': 'Here you will find a list of all the cards in this section (and its subsections if selected levels is greater than 2)',

      'CARDS-DOC-VIEW-TT': 'document view',
      'CARDS-DOC-VIEW-DET': 'Here you will see the contents of all the cards in this section (and its subsections if selected levels is greater than 2) displayed as a document',

      'REDUCE-LEVELS-TT': 'see less levels',
      'REDUCE-LEVELS-DET': LEVELS_DOWN_EN,

      'INCREASE-LEVELS-TT': 'see more levels',
      'INCREASE-LEVELS-DET': LEVELS_UP_EN,

      'SEE-ALL-LEVELS-TT': 'see all levels',
      'SEE-ALL-LEVELS-DET': LEVELS_ALL_EN,

      'PRIVATE-CARDS-TT': 'private cards',
      'PRIVATE-CARDS-DET': SCOPE_CARDS_BASE_EN,

      'SHARED-CARDS-TT': 'shared cards',
      'SHARED-CARDS-DET': SCOPE_CARDS_BASE_EN,

      'COMMON-CARDS-TT': 'common cards',
      'COMMON-CARDS-DET': SCOPE_CARDS_BASE_EN,

      'SHOW-SECTION-ORDER-TT': 'show cards ordered by sections',
      'SHOW-SECTION-ORDER-DET': 'This view will show all the cards of the selected sections (based on the current depth level) in their pre-specified per-section order. The number of cards shown will depend on the depth level only',

      'SEARCH-CARDS-TT': 'seach all cards under this section',
      'SEARCH-CARDS-DET': 'This will search all cards under this section (and all its subsecions) matching the input query. Only the top ten cards will be initially shown.',

      'SHOW-MESSAGES-TT': 'messages',
      'SHOW-MESSAGES-DET': 'This will show or hide messages from the activity timeline',

      'SHOW-EVENTS-TT': 'other events',
      'SHOW-EVENTS-DET': 'This will show or hide events (everything that is not a message) from the activity timeline',

      'DOWNLOAD-CONTENT-TT': 'download as text file',
      'DOWNLOAD-CONTENT-DET': 'This will download the cards currenlty being shown as a marked-down text file',

      'ENABLE-DRAG-AND-DROP-TT': 'drag and drop',
      'ENABLE-DRAG-AND-DROP-CARDS-DET': ENABLE_DD_CARDS_EN,
      'ENABLE-DRAG-AND-DROP-SECTIONS-DET': ENABLE_DD_SECTIONS_EN,

      'PRIVATE-SECTIONS-TT': 'private sections',
      'PRIVATE-SECTIONS-DET': SCOPE_SUBSECTIONS_BASE_EN,

      'SHARED-SECTIONS-TT': 'shared sections',
      'SHARED-SECTIONS-DET': SCOPE_SUBSECTIONS_BASE_EN,

      'COMMON-SECTIONS-TT': 'common sections',
      'COMMON-SECTIONS-DET': SCOPE_SUBSECTIONS_BASE_EN,

      'SECTION-DETAILS-TT': 'details',
      'SECTION-DETAILS-DET': 'One section can be a subsection of many parent sections at the same time. Here you can see a description of this section and all the places in which this section is present (those visible to you).',

      'READ-FRIENDLY-URL-TT': 'open read-only view',
      'READ-FRIENDLY-URL-DET': 'The read-only view will open a dedicated page which is perfect for sharing the contents of a section in a read-friendly version.<br><br>The view will include all COMMON cards and all COMMON subsections, at all depth levels.<br><br>You can share the link to that page with others, but access will depend on the visibility of the initiative.',

      'CONTENT-TAB-TT': 'initiative content',
      'CONTENT-TAB-DET': 'This view shows all the conversations and content of this initiative, organized in sections and cards:<br><br>- <b>A section</b> is a general purpose context, and it automatically provides a conversation space (like a #channel on a chat application), and a content space (similar to post-it boards) made out of cards.<br><br>- <b>A card</b> can contain text and (optionally) one image. Cards are designed to hold ideas, tasks, reminders or anything you want, however, because they are ordered within the section, cards can evolve to become paragraphs of a well structured section in a document.<br><br><b>Note:</b> Sections can be nested, allowing you to have nested conversation channels. Moreover, a section can be a subsection of many other sections at the same time, and a card can be included in many sections at the same time too, enabling you to flexibly aggregate the conversations and cards.',

      'MEMBERS-TAB-TT': 'initiative members',
      'MEMBERS-TAB-DET': 'This view shows all the members of this initiative and an aggregated list of the members of the subinitiatives.<br><br>Each initiative or subinitiative has its own, and independent, list of members, with their associated roles. Roles can be one of the following:<br><br>- ADMIN: who can edit the initiative details and create and transfer its assets/tokens.<br><br>- EDITOR: Who can create and edit sections and cards, but cant manipulate tokens.<br><br>- MEMBER: Who has access to the initiative (in case it is private) and can receive initiative assets.',

      'TRANSFERS-TAB-TT': 'initiative tokens',
      'TRANSFERS-TAB-DET': 'This view shows all the transfers of assets/tokens made in this initiative and an aggregated view of all the transfers made on all the subinitiatives.',

      'LANDING-BUTTON-TT': 'landing page',
      'LANDING-BUTTON-DET': 'You will find  video tutorials in the bottom of the page.',

      'HOME-BUTTON-TT': 'home page',
      'HOME-BUTTON-DET': 'In the home page you can browse public initiatives.',

      'PEER-REV-CONFIG-TT': 'Peer Review Configuration',
      'PEER-REV-CONFIG-DET': 'In a peer-review process, all the "evaluators" will be asked to propose a token distribution among the "receivers". <br><br><b>- Initial State:</b> If "on-hold", the evaluation period will not start immediately but can be started later. <br><br><b>- Max duration:</b> Once the evaluation period starts, the evaluators will have this amount of time to provide their inputs (in any case, the process is closed once all the evaluations are provided). <br><br><b>- Self-Bias Visible:</b> When enabled, the self-bias (difference between each evaluators self-evaluation and its final token attribution) will be visible<br><br><b>- All evaluations visible:</b> When enabled, the evaluations that each evaluator made on each receiver will be publicly visible.'
    }
  },

  es: {
    general: {
      'SHOW': 'mostrar',
      'HIDE': 'esconder',
      'ENABLE': 'activar',
      'DISABLE': 'desactivar',
      'LOGIN_SIGNUP': 'ENTRA / REGISTRATE',
      'NO_RESULTS_FOUND': 'no se ha encontrado ningún resultado',
      'CREATE_NEW': 'crear nuevo',
      'NAME': 'Nombre',
      'DESCRIPTION': 'Descripción',
      'REQUIRED': 'obligatorio',
      'FIELD_CANNOT_BE_EMPTY': 'este campo no puede estár vacio',
      'FIELD_TOO_LONG': 'este campo es demasiado largo',
      'FIELD_ONLY_LETTERS': 'este campo solo puede contener letras, números y espacios',
      'OK': 'ok',
      'YES': 'sí',
      'NO': 'no',
      'ACCEPT': 'Aceptar',
      'CANCEL': 'Cancelar',
      'CONFIRM': 'Confirmar',
      'ADD': 'añadir',
      'INHERIT': 'Heredar',
      'CUSTOM': 'Especficar',
      'EDIT': 'editar',
      'DELETE': 'borrar',
      'REMOVE': 'remover',
      'CREATE': 'Crear',
      'SAVE': 'Guardar',
      'NOTIFICATIONS': 'notificaciones',
      'CHANGE': 'cambiar',
      'UPLOAD_IMAGE': 'subir imagen',
      'NOTES': 'Notas',
      'CONFIGURATION': 'Configuración',
      'SHOW_MORE': 'mostrar más',
      'MORE': 'más',
      'EMPTY': 'vacío',
      'HOME': 'inicio',
      'PROFILE': 'perfil',
      'LOGOUT': 'salir',
      'PROFILE_CC': 'Perfil',
      'SETTINGS': 'Configuración',
      'LANGUAGE': 'Idioma'
    },
    landing: {
      'PRINCIPLES': 'PRINCIPIOS',
      'FEATURES': 'FUNCIONALIDADES',
      'DEMOS': 'DEMOS',
      'PARTICIPATE': 'PARTICIPA',
      'WELCOME': 'BIENVENIDO A',
      'BRIEF': 'CollectiveOne es un método (y una plataforma) para desarrollar proyectos abiertos, descentralizados y colaborativos: projectos a los cuales cualquier persona puede, potencialmente, contribuir, y que pertenecen y son collectivamente gestionados por sus contribuidores. <br><br>Ah! y de paso es una herramienta de colaboración súper potente para equipos "normales".',
      'EXPLORE': 'EXPLORAR',
      'OPEN_COLLAB': 'COLABORACIÓN ABIERTA',
      'OPEN_COLLAB_CONTENT': 'Similar a los proyectos de código abierto, las barreras de entrada a un proyecto son reducidas, permitiendo a cualquiera contribuir a un proyecto en condiciones similares a contribuidores previos.',
      'CONTRIB_REC': 'RECONOCIMIENTO DE LAS CONTRIBUCIONES',
      'CONTRIB_REC_CONTENT': 'Las contribuciones son reconocidas y valoradas relativamente a las demás utilizando un token (o moneda) propia de cada proyecto.',
      'LIQUID_OWNERSHIP': 'PROPIEDAD LÍQUIDA',
      'LIQUID_OWNERSHIP_CONTENT': 'La propiedad del proyecto está directamente ligada a las contribuciones realizadas y, por tanto, a los tokens. Entre más contribuyes, más participaciones del proyecto tendrás.',
      'DIST_GOV': 'GOBERNANZA DISTRIBUÍDA',
      'DIST_GOV_CONTENT': 'Los procesos de gobernanza pueden ser configurados de forma flexible, permitiendo que la gestión y la toma de decisiones sean procesos abiertos y transparentes.',
      'TRANSP': 'TRANSPARENCIA',
      'TRANSP_CONTENTS': 'La información de un proyecto puede ser pública, facilitando así que otras personas contribuyan.',
      'VALUE_ACC': 'PROCESOS DE VALORACIÓN',
      'VALUE_ACC_CONTENT': 'Procesos y herramientas que facilitan el uso de los tokens transfiriéndolos a contrubidores y subproyectos.',
      'COLL_VISION': 'VISIÓN COLECTIVA',
      'COLL_VISION_CONTENT': 'Un módulo dedicado a la comunicación, presentación e intercambio de ideas y propuestas entre los contribuidores de un proyecto',
      'TASK_MAN': 'GESTIÓN DE TAREAS',
      'TASK_MAN_CONTENT': 'Sistema flexible de organización de la información y de valoración de las tareas.',
      'DISTR_GOB': 'GOBERNANZA DISTRIBUIDA',
      'DISTR_GOB_CONTENT': 'Procesos que faciltan la toma de decisiones de forma abierta y eficiente.',
      'FLEXIBLE_CHANNELS': 'Red de canales de comunicación',
      'FLEXIBLE_CHANNELS_CONTENTS': 'Colaborar es posible gracias a la comunicación. En CollectiveOne, los canales de comunicación de un proyecto pueden ser interconectados de forma flexible para representar mejor la estructura real de una organización',
      'PERSONALIZED_CHANNELS': 'Perspectiva Propia',
      'PERSONALIZED_CHANNELS_CONTENTS': 'Cada persona es un mundo. En CollectiveOne, cada usuario puede personalizar los canales de comunicación y su contenido de la manera que quiera.',
      'CHANNEL_CONTENTS': 'Tarjetas',
      'CHANNEL_CONTENTS_CONTENTS': 'Además conversaciones, cada canal puede tener contenido estático (que no desaparece en el historial), útil para guardar información relevante, anuncios, propuestas o acuerdos.',
      'PARTICIPATE_TEXT': 'CollectiveOne es un proyecto abierto en sí mismo y está siendo desarrollado usando <b><a href="http://www.collectiveone.org/#/app/inits/ac119496-5e3e-1db5-815e-3f192a890001">CollectiveOne mismo</a></b>. Las contribuciones son bienvenidas!. <br><br>Regístrate y visita <b><a href="http://www.collectiveone.org/#/app/inits/ac119496-5e3e-1db5-815e-3f192a890001/people">la lista de miembros de CollectiveOne</a></b> donde involucrarte.',
      'FOLLOW_US': 'SÍGUENOS!'
    },
    initiatives: {
      'INITIATIVE': 'iniciativa',
      'MY_INITIATIVES': 'Mis Iniciativas',
      'PUBLIC_INITIATIVES': 'Iniciativas Públicas',
      'MY_INITS_EMPTY': 'aún no haces parte de ninguna iniciativa',
      'NEW_SUBINITIATIVE': 'crear subiniciativa',
      'CREATE_ONE': 'crear una',
      'NEW': 'nueva iniciativa',
      'CURRENT_INITIATIVE': 'Iniciativa Actual',
      'TAG': 'Etiqueta',
      'TAGS': 'Etiquetas',
      'FILTER_BY_TAG': 'Filtrar por etiquetas',
      'SELECT_TAG': 'seleccionar etiqueta',
      'NEW_INIT_MODAL': 'Nueva Iniciativa',
      'CREATE_NEW_TOKEN_Q': 'Crear un nuevo token?',
      'ONE_ADMIN_ATLEAST': 'debe existir al menos un administrador',
      'INITIAL_MEMBERS': 'Miembros Iniciales',
      'NEW_SUBINITIATIVE_OF': 'Nueva subiniciativa de',
      'EDIT_INITIATIVE': 'Editar Iniciativa',
      'COLOR': 'Color',
      'VISIBILITY': 'Visibilidad',
      'PRIVATE': 'privada',
      'ECOSYSTEM': 'ecosistema',
      'INHERIT': 'heredar',
      'PUBLIC': 'pública',
      'DELETE_INIT': 'Borrar Iniciativa',
      'CONFIRM_DELETE_MSG': '<b>Atención:</b> Se borrará la iniciativa junto con todas sus subiniciativas y, en caso de haber una iniciativa madre, se le transferirán todos los tokens a ella. Por favor confirme.',
      'DELETE_WARNING': '<b>Atención:</b> Confirma que desea borrar la iniciativa "{name}"? Esto borrará todos sus contenidos.',
      'SUBINITIATIVES': 'SUB-INICIATIVAS'
    },
    members: {
      'WANT_TO_CONTRIB_Q': 'Quieres contribuir?',
      'WANT_TO_CONTRIB_MSG': 'Los administradores de esta iniciativa serán notificados de su interes y podrán añadirle como contribuidor.',
      'ADMINS_NOTIFIED': 'Los administradores de esta iniciative han sido notificados correctamente.',
      'USER_ADDED_SUCCESS': 'El usuario ha sido correctamente añadido.',
      'ERROR_NOTIFYING': 'Error durante el proceso de notificación de los administradores.',
      'MEMBERS_OF': 'Miembros de {title}',
      'MEMBERS_OF_SUBS': 'Miembros de sub-iniciativas de {title}',
      'ADD_USER': 'añadir usuario',
      'CHOOSE_ROLE': 'escoger rol',
      'ADMIN': 'ADMIN',
      'EDITOR': 'EDITOR',
      'MEMBER': 'MIEMBRO',
      'AVATAR': 'AVATAR',
      'NICKNAME': 'NICKNAME',
      'ROLE': 'ROL',
      'DELETE': 'BORRAR',
      'LEAVE': 'ABANDONAR'
    },
    tokens: {
      'NUMBER_OF_TOKENS': 'Número de Tokens',
      'TOKEN_NAME': 'Nombre del Token',
      'TOKENS': 'Tokens',
      'TRANSFERS_HISTORY_FROM': 'Historial de transferencias desde {initName}',
      'TRANSFERS_HISTORY_FROM_SUBINITIATIVES_OF': 'Historial de transferencias desde sub-iniciativas de {initName}',
      'TRANSFER_ASSETS_Q': 'Transferir tokens?',
      'NO_ASSETS_TRANSFERRED_CONF': 'Atención: No han sido seleccionados el número de tokens a transferir. Si es correcto, por favor confirme más abajo, de lo contrario, especifique cuántos tokens transferir.',
      'NO_ASSETS_TRANSFERRED_MSG': 'Confirma que no será transferido ningún token desde {initName} a esta nueva sub-iniciativa?',
      'TRANSFER_FROM': 'Transferir desde {initName}',
      'TOKEN_TO_TRANSFER': 'Token a transferir',
      'NO_ASSETS_IN_PARENT': 'La iniciativa madre no posee ningún token',
      'OF_EXISTING': 'de los existentes',
      'STILL_AVAILABLE': 'Aún disponibles',
      'UNDER_PENDING_PR': 'En proceso de transferencia co-evaluada',
      'TRANSFERRED_TO_SUBS': 'Transferidos a sub-iniciativas',
      'TRANSFERRED_TO_MEMS': 'Transferidos a miembros',
      'AMOUNT_TO_TRANSF': 'Cantidad a ser transferida',
      'AVAILABLE': 'disponibles',
      'NO_ASSETS_HELD': 'Esta iniciativa aún no posee ningún token',
      'TRANSFER_TO_INITIATIVE': 'Transferir tokens a otras iniciativas',
      'TRANSFER_TO': 'Transferir a ',
      'ZERO_TOKENS_ERROR': 'Selecciones cuántos tokens transferir',
      'MOTIVE': 'Motivo',
      'NO_PARENTS_ERROR': '{name} no tiene iniciativas madre o sub-iniciativas',
      'TRANSFER_TO_USERS': 'Transferir tokens a usuarios',
      'PEER_REVIEW_TITLE': 'Transferir tokens con proceso de co-evaluación',
      'DIRECT_TITLE': 'Transferir tokens de forma directa',
      'DIRECT': 'Directa',
      'PEER_REVIEWED': 'Co-evaluada',
      'NOT_ENOUGH_RECEIVERS': 'por favor añada al menos un receptor',
      'NOT_ENOUGH_EVALS': 'por favor añada al menos un evaluador',
      'NOT_ALL_DONORS': 'no todos los receptores pueden ser donantes',
      'PERCENTAGES_WRONG': 'los porcentajes deben sumar el 100%',
      'NO_ASSETS_SELECTED': 'atención: usted no ha seleccionado ningún token. Por favor confirme que esto es correcto o especifique la cantidad de tokens a transferir.',
      'INITIAL_STATE': 'Estado Inicial',
      'ON_HOLD': 'pendiente',
      'OPEN': 'abierto',
      'MAX_DURATION': 'Duración max.',
      'DAYS': 'día | días',
      'SELF_BIAS_VISIBLE': 'Auto-sesgo visible',
      'ALL_EVALS_VISIBLE': 'Evaluaciones visibles',
      'NO_ASSETS_TRASFERRED': 'Usted no transferirá tokens a estos usuarios. Por favor confirme que esto es correcto.',
      'NO_MEMBERS_SELECTED': 'ningñun miembro ha sido seleccionado',
      'ONE_RECEIVER': 'un recetor',
      'MULTIPLE_RECEIVERS': 'múltiples receptores',
      'RECEIVERS': 'Receptores',
      'DONOR_WARNING': '<b>Atención:</b> {{ donorUsersStr }} será parte del proceso de evaluación pero sus tokens serán distribuidos entre el resto de receptores.',
      'EVALUATORS': 'Evaluadores',
      'SAME_AS_RECEIVERS': 'serán los mismos que los receptores',
      'DIFF_FROM_RECEIVERS_Q': 'diferentes a los receptores?',
      'DATE_UC': 'FECHA',
      'FROM_UC': 'DE',
      'VALUE_UC': 'VALOR',
      'TO_UC': 'A',
      'MOTIVE_UC': 'MOTIVO',
      'STATUS_UC': 'ESTADO',
      'TYPE_UC': 'TIPO',
      'FROM_NAME': 'de <b>{name}</b>',
      'REVERT_TX': 'revertir transferencia',
      'REVERT_WARNING': '<b>Atención:</b> Usted solicitará a todos los receptores devolver los tokens a la iniciativa. Por favor confirme que esto es correcto.',
      'DELETE_WARNING': '<b>Atención:</b> Usted borrará esta transferencia de tokens. Las evaluaciones recibidas hasta la fecha se perderán. Por favor confirme.',
      'OPEN_START_EVALS': 'abrir/iniciar evaluaciones',
      'OPEN_WARNING': '<b>Atención:</b> Usted iniciará el periodo de co-evaluación. Los evaluadores tendán {days} días para ingresar sus evaluaciones.',
      'REVERT_APPROVE_WARNING': '<b>Atención:</b> Uno de los administradores desea revertir esta transferencia. Apruebas devolver los tokens que has recibido?',
      'REJECT': 'Rechazar',
      'APPROVE': 'Aprovar',
      'APPROVED_MSG': 'aprovado!',
      'PEER_REV_STATUS': '{nEvalsPending} evaluaciones aún pendientes, tiempo restante {timeRemaining}',
      'PEER_REV_TIP': '<b>Consejo:</b> Puedes rellenar los valores con números que tengan sentido relativamente entre sí, <b>incluso si estos suman más o menos del 100%</b>, y luego hacer click en el botón de "auto-escalar"',
      'MY_EVAL': 'Mi evaluación',
      'USER_UC': 'USUARIO',
      'DONOR_UC': 'DONANTE',
      'MINE_UC': 'MÍAS',
      'PRE_DONOR_UC': 'PRE-DONANTE',
      'FINAL_UC': 'FINAL',
      'FINAL_TOKENS_UC': 'TOKENS FINALES',
      'TOTAL_UC': 'TOTAL',
      'KNOW_DONT_UC': 'SÉ / NO SÉ',
      'SELF_BIAS_UC': 'AUTO-SESGO',
      'REMOVE_UC': 'REMOVER',
      'DONT_KNOW': ' NO SÉ',
      'DONT_KNOW_SHORT': 'NS',
      'TOTAL_ASSIGNED': 'total asignados',
      'AUTO_ROUNDED': 'auto-redondeo',
      'AUTOSCALE': 'auto-ecalar',
      'CHANGE_MY_EVAL': 'Cambiar mi evaluación',
      'RESULTS': 'Resultados',
      'ALL_EVALS': 'Todas las Evaluaciones'
    },
    activity: {
      'EDIT_NOTIFICATIONS_OF': 'Configurar las notifications para la {type} {title}',
      'EDIT_GLOBAL_NOTIFICATIONS': 'Configuración global de las notificaciones',
      'GENERAL_CONF': 'Configuración General',
      'INHERITING_FROM': 'Heredar las preferencias de notificación de: <br><b>{name}</b>',
      'DISABLE': 'desactivar',
      'ONLY_MENTIONS': 'sólo menciones',
      'ONLY_MESSAGES': 'sólo mensajes',
      'ALL_EVENTS': 'todo',
      'IN_APP_NOTIFICATIONS': 'Notificaciones en el app',
      'PUSH_NOTIFICATIONS_MSG': 'Notificaciones en el dispositivo (push):<br><small>Enviadas únicamente cuando no estás activo</small>',
      'IMMEDIATE_MAIL_MSG': 'Correo inmediato:<br><small>Enviado únicamente cuando no estás activo</small>',
      'EMAIL_SUMMARY_MSG': 'Correo de resumen:<br><small>Enviado únicamente cuando no se han visto en el app</small>',
      'DAILY': 'diario',
      'WEEKLY': 'semanal',
      'TITLE_AND_TYPE': '{type} "{title}"',
      'GLOBAL_NOTIFICATIONS_PREFS': 'Tu configuración global de notificaciones'
    },
    model: {
      'SECTION': 'sección',
      'NEW_SECTION': 'Nueva Sección',
      'MODEL_SECTION': 'Sección',
      'AS_TOP_LEVEL_IN': 'Como sección principal en esta iniciativa',
      'IN_SECTION': 'En la sección',
      'CREATE_NEW': 'Crear Nueva',
      'ADD_EXISTING': 'Añadir Existente',
      'SCOPE_IN': 'Visiblidad (en la sección "{title}")',
      'NEW_SCOPE_IN': 'Nueva visiblidad (en la sección "{title}")',
      'PRIVATE': 'privada',
      'SHARED': 'compartida',
      'COMMON': 'común',
      'PRIVATE_MSG': 'Privada (visible sólo para tí)',
      'SHARED_MSG': 'Compartida (visible para los demas miembros de la iniciativa, pero solo tú la puedes editar)',
      'COMMON_MSG': 'Común (visible al público si la iniciativa es pública y editable por otros miembros)',
      'TITLE': 'Título',
      'CONTENT': 'Contenido',
      'CLOSE_WARNING': 'Estás editando esta sección, estás segure que quieres cerrar este diálogo, los cambios se perderán.',
      'SEARCH_AND_SELECT_SECTION': 'busca y selecciona una sección',
      'DELETE_CARD_WARNING': '<b>Atención:</b> Deseas borrar la tarjeta del sistema y de todas las secciones donde esta se encuentre?',
      'REMOVE_CARD_WARNING': '<b>Atención:</b> Deseas borrar la tarjeta de la sección {title}?',
      'ADD_SUBSECTION': 'añadir subsección',
      'REMOVE_SECTION_WARNING': '<b>Atención:</b> Está seguro que desea remover la sección "{sectionTitle}" como subsección de "{inSectionTitle}"?',
      'DELETE_SECTION_WARNING': '<b>Atención:</b> Está seguro que desea eliminar por completp la sección "{sectionTitle}"? Esto borrará la sección de todos los lugares en los que haya sido añadida como subsección.'
    },
    help: {
      'MESSAGES-TAB-TT': 'mensajes',
      'MESSAGES-TAB-DET':
      'Aquí encontrarás todos los mensajes y eventos que han ocurrido dentro de esta sección. La lista incluye también todos los mensajes y eventos que han ocurrido en las sub-secciones.',

      'CARDS-SUMMARY-TAB-TT': 'resumen de tarjetas',
      'CARDS-SUMMARY-TAB-DET': 'Aquí encontrarás una vista <b>resumida</b> de la lista de tarjetas contenidas en esta sección (y también en sus subsecciones si has seleccionado un nivel de vista mayor a 1)',

      'CARDS-TAB-TT': 'tarjetas',
      'CARDS-TAB-DET': 'Ver todas las tarjetas contenidas en esta sección (y también en sus subsecciones si has seleccionado un nivel de vista mayor a 1) visalizadas en formato de post-its',

      'CARDS-DOC-VIEW-TT': 'vista tipo documento',
      'CARDS-DOC-VIEW-DET': 'Ver todas las tarjetas contenidas en esta sección (y también en sus subsecciones si has seleccionado un nivel de vista mayor a 1) visalizadas en formato documento',

      'REDUCE-LEVELS-TT': 'ver menos niveles',
      'REDUCE-LEVELS-DET': LEVELS_DOWN_ES,

      'INCREASE-LEVELS-TT': 'ver más niveles',
      'INCREASE-LEVELS-DET': LEVELS_UP_ES,

      'SEE-ALL-LEVELS-TT': 'ver todos los niveles',
      'SEE-ALL-LEVELS-DET': LEVELS_ALL_ES,

      'PRIVATE-CARDS-TT': 'tarjetas privadas',
      'PRIVATE-CARDS-DET': SCOPE_CARDS_BASE_ES,

      'SHARED-CARDS-TT': 'tarjetas compartidas',
      'SHARED-CARDS-DET': SCOPE_CARDS_BASE_ES,

      'COMMON-CARDS-TT': 'tarjetas comunes',
      'COMMON-CARDS-DET': SCOPE_CARDS_BASE_ES,

      'SHOW-SECTION-ORDER-TT': 'ver tarjetas ordenadas según sus secciones',
      'SHOW-SECTION-ORDER-DET': 'En esta vista, las tarjetas son mostradas en el orden dado por su ubicación dentro de cada sección y por el orden de las sub-secciones. La cantidad de tarjetas mostradas depende del nivel de profundidad seleccionado.',

      'SEARCH-CARDS-TT': 'buscar tarjetas dentro de esta sección',
      'SEARCH-CARDS-DET': 'Aquí puedes buscar y filtrar todas las tarjetas de esta sección (y sus subsecciones) con base en un criterio o palabra clave.',

      'SHOW-MESSAGES-TT': 'mensajes',
      'SHOW-MESSAGES-DET': 'Muestra o esconde los mensajes del contenido del chat',

      'SHOW-EVENTS-TT': 'otros eventos',
      'SHOW-EVENTS-DET': 'Muestra o esconde los eventos (todo lo que no es un mensaje) del contenido del chat',

      'DOWNLOAD-CONTENT-TT': 'descargar como fichero de texto',
      'DOWNLOAD-CONTENT-DET': 'Aquí puedes decargar el contenido de todas las tarjetas actualmente mostradsa como un fichero de texto anotado con "markdown"',

      'ENABLE-DRAG-AND-DROP-TT': 'arrastrar',
      'ENABLE-DRAG-AND-DROP-CARDS-DET': ENABLE_DD_CARDS_ES,
      'ENABLE-DRAG-AND-DROP-SECTIONS-DET': ENABLE_DD_SECTIONS_ES,

      'PRIVATE-SECTIONS-TT': 'secciones privadas',
      'PRIVATE-SECTIONS-DET': SCOPE_SUBSECTIONS_BASE_ES,

      'SHARED-SECTIONS-TT': 'secciones compartidas',
      'SHARED-SECTIONS-DET': SCOPE_SUBSECTIONS_BASE_ES,

      'COMMON-SECTIONS-TT': 'secciones comunes',
      'COMMON-SECTIONS-DET': SCOPE_SUBSECTIONS_BASE_ES,

      'SECTION-DETAILS-TT': 'detalles',
      'SECTION-DETAILS-DET': 'Una sección puede contener subsecciones las cuales pueden pertenecer a varias secciones contemporáneamente. Aquí puedes ver la descripción de esta sección, así como todos las secciones madre (visibles para tí) donde esta se encuentra.',

      'READ-FRIENDLY-URL-TT': 'vista de lectura',
      'READ-FRIENDLY-URL-DET': 'La vista de lectura genera un enlace ideal para compartir el contenido de una sección para su lectura. <br><br>Esta vista incluye todas las tarjetas y subsecciones COMUNES a todos los niveles de profundidad. <br><br>Puedes compartir este enlace con otras personas, pero el acceso para la lectura dependerá de la configuración de visiblidad de la iniciativa.',

      'CONTENT-TAB-TT': 'contenido',
      'CONTENT-TAB-DET': 'En esta vista encontrarás todas las conversaciones y el contenido de esta iniciativa organizadas por secciones y tarjetas:<br><br>- <b>Una sección</b> es en general un contexto o tema y provee un canal de comunicación y un espacio para agregar tarjetas.<br><br>- <b>Una tarjeta</b> puede contener texto y (opcionalmente) una imagen. Las tarjetas están diseñadas para contener ideas, tareas, recordatorios o cualquier tipo de contenido, sin embargo, al estar organizadas dentro de la sección, su contenido puede evolucionar hasta convertirse en párrafos dentro de una sección de un documento bien estructurado.<br><br><b>Nota:</b> Las secciones pueden ser agrupuadas entorno a otras permitiendo agrupar diferentes conversaciones. Adicionalmente, una sección puede ser subsección de varias secciones contemporáneamente, permitiendo agregar conversaciones y tarjetas de forma flexible.',

      'MEMBERS-TAB-TT': 'miembros',
      'MEMBERS-TAB-DET': 'En esta vista encontrarás la lista de miembros de esta iniciativa así como la lista de miembros de todas sus sub-iniciativas.<br><br>Cada iniciativa o sub-iniciativa tiene su propia lista independiente de miembros con sus roles asociados. Los roles pueden ser uno de los siguientes: <br><br>- ADMIN: Quien puede editar la iniciativa y manipular sus tokens.<br><br>- EDITOR: Quien puede crear y editar el contenido común de la iniciativa (secciones y tarjetas) pero no puede manipular sus tokens.<br><br>- MIEMBRO: Quien tiene accceso al contenido de la iniciativa y puede recibir sus tokens.',

      'TRANSFERS-TAB-TT': 'tokens',
      'TRANSFERS-TAB-DET': 'En esta vista encontrarás los tokens que esta iniciativa posee, su distribución entre los miembros y las sub-iniciativas, así como un historial de las transferencias realizadas.',

      'LANDING-BUTTON-TT': 'ayuda',
      'LANDING-BUTTON-DET': 'Visita los videos con demos sobre la herramienta.',

      'HOME-BUTTON-TT': 'inicio',
      'HOME-BUTTON-DET': 'En tu página de inicio podrás ver tus iniciativas o explorar iniciativas públicas.',

      'PEER-REV-CONFIG-TT': 'Configuración del proceso de Co-Evaluación',
      'PEER-REV-CONFIG-DET': 'En un proceso de co-evaluación, se solicitará a los "evaluadores" que propongan una distribución de una cierta cantidad de tokens entre los "receptores". <br><br><b>- Estado Inicial:</b> En case de comenzar en "pendiente", el periodo de evaluación no comenzará a contar inmediatamente sino que deberá ser lanzado manualmente luego de crear la transferencia. <br><br><b>- Duración max.:</b> Una vez el periodo de evaluación comienza, los evaluadores tendran este tiempo para realizar su evaluación (en cualquier case, el proceso termina una vez que todos los evaluadores hayan realizado su aportación. <br><br><b>- Auto-sesgo Visible:</b> Cuando está activo, el auto-sesgo (diferencia entre la propia evaluación de un evaluador y su  atribución de tokens final) será visible.<br><br><b>- Evaluaciones visibles:</b> Cuando está activo, todas las evaluaciones de todos los evaluadores serán publicas.'
    }
  }
}

export {
  translations
}
