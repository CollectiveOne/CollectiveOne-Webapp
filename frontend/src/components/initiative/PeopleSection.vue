<template lang="html">
  <div class="people-container">
    <div v-if="showWantToContribute" class="w3-row w3-center top-button-row">
      <div class="w3-row">
        <button @click="wantToContributeSelected = !wantToContributeSelected"
          class="w3-button app-button" type="button" name="button">
          {{ $t('members.WANT_TO_CONTRIB_Q') }}
        </button>
      </div>
      <div class="w3-row w3-margin-top">
        <div class="slider-container">
          <transition name="slideDownUp">
            <div v-if="wantToContributeSelected" class="w3-row tags-row w3-center">
              <div class="w3-padding w3-round light-grey w3-margin-bottom">
                <p>{{ $t('members.WANT_TO_CONTRIB_MSG') }}</p>
              </div>

              <button
                class="w3-button app-button-light button-pair"
                @click="wantToContributeSelected = false">
                {{ $t('general.CANCEL') }}
              </button>
              <button
                class="w3-button app-button button-pair"
                @click="wantToContribute()">
                {{ $t('general.CONFIRM') }}
              </button>
            </div>
          </transition>
        </div>
      </div>
    </div>
    <app-error-panel
      :show="requestSent"
      :message="$t('members.ADMINS_NOTIFIED')"
      panelType="success">
    </app-error-panel>
    <app-error-panel
      :show="userAdded"
      :message="$t('members.USER_ADDED_SUCCESS')"
      panelType="success">
    </app-error-panel>
    <app-error-panel
      :show="requestError"
      :message="$t('members.ERROR_NOTIFYING')">
    </app-error-panel>
    <div class="w3-row own-members-div">
      <h3 class="section-header"> {{ $t('members.MEMBERS_OF', { title: initiative.meta.name }) }}:</h3>
      <app-members-table
        :members="initiative.initiativeMembers.members"
        :canEdit="isLoggedAnAdmin || isLoggedAParentAdmin"
        :assets="initiative.assets"
        @remove="removeMember($event)"
        @role-updated="roleUpdated($event)"
        @add="addMember($event)">
      </app-members-table>
      <div v-if="noOtherAdminError" class="w3-row w3-tag error-panel error-row w3-round">
        {{ $t('initiatives.ONE_ADMIN_ATLEAST') }}
      </div>
    </div>
    <br>
    <div v-if="allSubmembers.length > 0" class="sub-members-div">
      <hr>
      <h3 class="section-header">{{ $t('members.MEMBERS_OF_SUBS', { title: initiative.meta.name }) }}:</h3>
      <app-submembers-table
        :submembers="allSubmembers"
        :assets="initiative.assets">
      </app-submembers-table>
    </div>

  </div>
</template>

<script>
import { mapActions } from 'vuex'
import MembersTable from '@/components/user/MembersTable.vue'
import SubmembersTable from '@/components/user/SubmembersTable.vue'

const getIndexOfUser = function (list, c1Id) {
  for (var ix in list) {
    if (list[ix].user.c1Id === c1Id) {
      return ix
    }
  }
  return -1
}

const appendMembersAndSubmembers = function (initiativeMembers, allmembers) {
  allmembers = allmembers || []
  initiativeMembers.members.forEach((member) => {
    /* Add this initiative members */
    var initiativeData = {
      name: initiativeMembers.initiativeName,
      id: initiativeMembers.initiativeId
    }

    var ix = getIndexOfUser(allmembers, member.user.c1Id)
    if (ix === -1) {
      allmembers.push({
        user: member.user,
        subinitiatives: [ initiativeData ],
        receivedAssets: member.receivedAssets
      })
    } else {
      allmembers[ix].subinitiatives.push(initiativeData)
    }
  })
  for (var ix in initiativeMembers.subinitiativesMembers) {
    appendMembersAndSubmembers(initiativeMembers.subinitiativesMembers[ix], allmembers)
  }
}

const getAllSubmembers = function (initiativeMembers) {
  var submembers = []
  initiativeMembers.subinitiativesMembers.forEach((subinitiativeMembers) => {
    appendMembersAndSubmembers(subinitiativeMembers, submembers)
  })
  return submembers
}

export default {
  components: {
    'app-members-table': MembersTable,
    'app-submembers-table': SubmembersTable
  },

  data () {
    return {
      noOtherAdminError: false,
      wantToContributeSelected: false,
      userAdded: false,
      requestSent: false,
      requestError: false
    }
  },

  computed: {
    initiative () {
      return this.$store.state.initiative.initiative
    },
    isLoggedAnAdmin () {
      return this.$store.getters.isLoggedAnAdmin
    },
    isLoggedAParentAdmin () {
      return this.$store.getters.isLoggedAParentAdmin
    },
    isLoggedAMember () {
      return this.$store.getters.isLoggedAMember
    },
    allSubmembers () {
      return getAllSubmembers(this.initiative.initiativeMembers)
    },
    showWantToContribute () {
      return this.$store.state.user.authenticated && !this.isLoggedAMember && !this.isLoggedAParentAdmin && !this.requestSent
    },
    addMemberLink () {
      /* based on the route */
      for (var ix in this.$route.matched) {
        let e = this.$route.matched[ix]
        if (e.name === 'InitiativePeopleAddMember') {
          return true
        }
      }
      return false
    }
  },

  methods: {
    ...mapActions(['showOutputMessage']),

    wantToContribute () {
      this.wantToContributeSelected = false
      this.axios.post('/1/initiative/' + this.initiative.id + '/wantToContribute').then((response) => {
        if (response.data.result === 'success') {
          this.requestSent = true
        } else {
          this.requestError = true
        }
      }).catch(() => {
        this.requestError = true
      })
    },

    addMember (member) {
      var index = this.indexOfMember(member.user.c1Id)
      member.initiativeId = this.initiative.id

      if (index === -1) {
        this.axios.post('/1/initiative/' + this.initiative.id + '/member', member).then((response) => {
          if (response.data.result === 'success') {
            this.$store.dispatch('refreshInitiative')
          } else {
            this.showOutputMessage(response.data.message)
          }
        })
      } else {
        this.showOutputMessage('user has been already included')
      }

      /* show message */
      this.userAdded = true
      setTimeout(() => {
        this.userAdded = false
      }, 3000)
    },

    removeMember (member) {
      var index = this.indexOfMember(member.user.c1Id)
      if (index > -1) {
        this.axios.delete('/1/initiative/' + this.initiative.id + '/member/' + member.user.c1Id).then((response) => {
          if (response.data.result === 'success') {
            this.$store.dispatch('refreshInitiative')
          } else {
            this.showOutputMessage(response.data.message)
          }
        })
      }
    },

    roleUpdated (member) {
      var index = this.indexOfMember(member.user.c1Id)
      if (index > -1) {
        var otherAdmin = false
        for (var ix in this.initiative.initiativeMembers.members) {
          if (ix !== index) {
            if (this.initiative.initiativeMembers.members[ix].role === 'ADMIN') {
              otherAdmin = true
            }
          }
        }

        if (otherAdmin) {
          this.axios.put('/1/initiative/' + this.initiative.id + '/member/' + member.user.c1Id, member
          ).then((response) => {
            if (response.data.result === 'success') {
              this.$store.dispatch('refreshInitiative')
            } else {
              this.showOutputMessage(response.data.message)
            }
          })
        } else {
          this.noOtherAdminError = true
          this.initiative.initiativeMembers.members[index].role = 'ADMIN'
          setTimeout(() => {
            this.noOtherAdminError = false
          }, 2000)
        }
      }
    },

    indexOfMember (c1Id) {
      for (var ix in this.initiative.initiativeMembers.members) {
        if (this.initiative.initiativeMembers.members[ix].user.c1Id === c1Id) {
          return ix
        }
      }
      return -1
    }
  },

  mounted () {
    if (this.addMemberLink) {
      var member = {
        user: {
          c1Id: this.$route.params.userId,
          role: 'MEMBER'
        }
      }
      this.addMember(member)
    }
  }
}
</script>

<style scoped>

.people-container {
  overflow: auto;
  padding-top: 0px !important;
  padding-bottom: 25px !important;
  height: 100%;
}

.top-button-row {
  padding-top: 30px;
  padding-bottom: 30px;
}

.members-div {
  padding-top: 20px;
  padding-bottom: 20px;
}

.members-panel {
  margin-bottom: 25px;
}

.initiative-member-row {
  margin-bottom: 10px;
}

.subinitiatives-tags-container {
  padding-top: 17px;
}

.subinitiative-tag {
  margin-right: 10px;
  margin-bottom: 10px;
  padding: 3px 10px 3px 10px;
}

</style>
