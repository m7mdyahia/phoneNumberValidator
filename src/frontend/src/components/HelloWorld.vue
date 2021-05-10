<template>
  <div class="container">
    <div class="row">
      <div class="panel panel-primary filterable">
        <div class="panel-heading">
          <h1 class="panel-title">Phone Numbers</h1>
        </div>
        <table class="table table-striped table-hover table-responsive">
          <thead>
          <tr>
            <th>
              Phone
            </th>
            <th>
              <div class="dropdown">
                Country Name
                <button class="btn btn-secondary btn-sm" type="button" id="countryNameFilter"
                        data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
                  <BootstrapIcon
                      icon="funnel"
                      size="small"/>
                </button>
                <ul class="dropdown-menu" aria-labelledby="countryNameFilter">
                  <li v-for="country in countries" :key="country.id">
                    <a class="dropdown-item">
                      <input type="checkbox" v-model="country.checked" @change="filter">
                      {{ country.name }}
                    </a>
                  </li>
                </ul>
              </div>
            </th>
            <th>
              <div class="dropdown">
                Status
                <button class="btn btn-secondary btn-sm" type="button" id="statusFilter"
                        data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
                  <BootstrapIcon
                      icon="funnel"
                      size="small"/>
                </button>
                <ul class="dropdown-menu" aria-labelledby="statusFilter">
                  <li v-for="status in statuses" :key="status.name">
                    <a class="dropdown-item">
                      <input type="checkbox" v-model="status.checked" @change="filter">
                      {{ status.name }}
                    </a>
                  </li>
                </ul>
              </div>
            </th>
            <th>
              Country Code
            </th>
            <th>
              Phone
            </th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="customer in customers" :key="customer.phone.number">
            <td>{{ customer.phone.fullNumber }}</td>
            <td>
              <template v-if="customer.phone.country">
                {{ customer.phone.country.name }}
              </template>
            </td>
            <td :class="{red:!customer.phone.valid}">
              {{ customer.phone.valid ? 'valid' : 'invalid' }}
            </td>
            <td>
              <template v-if="customer.phone.country">
                {{ customer.phone.country.code }}
              </template>
            </td>
            <td>{{ customer.phone.number }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<style>
.red {
  background-color: red !important
}
</style>
<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      customers: {},
      countries: {},
      statuses: [
        {
          name: "valid",
          value: true,
          checked: true
        },
        {
          name: "in-valid",
          value: false,
          checked: true
        }
      ]
    }
  }, methods: {
    filter() {
      let selectedCountries = this.countries.filter(c => c.checked).map(c => c.id)
      let selectedStatuses = this.statuses.filter(c => c.checked).map(c => c.value)
      let data = {selectedCountries:selectedCountries,selectedStatuses:selectedStatuses};
      console.log(data);
      fetch('/api/customer/search', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      })
          .then(response => response.json())
          .then(data => {
            this.customers = data;
          });
    }
  },
  mounted() {
    fetch("/api/customer")
        .then(response => response.json())
        .then(data => {
          this.customers = data;
        });
    fetch("/api/country")
        .then(response => response.json())
        .then(data => {
          data.forEach(c => c.checked = true)
          return data
        })
        .then(data => {
          this.countries = data;
        })
  }
}
</script>