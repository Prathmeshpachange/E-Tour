using AutoMapper;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Models;
using SafarnamaApplication.Repositories;

namespace SafarnamaApplication.Services
{
    public class PackageService : IPackageService
    {
         private readonly IPackageRepository _packageRepository;
        private readonly IMapper _mapper;

        public PackageService(IPackageRepository packageRepository, IMapper mapper)
        {
            _packageRepository = packageRepository;
            _mapper = mapper;
        }

        public async Task<IEnumerable<PackageDTO>> GetAllPackages()
        {
            var packages = await _packageRepository.GetAllPackages();
            return _mapper.Map<IEnumerable<PackageDTO>>(packages);
        }

        public async Task<PackageDTO?> GetPackageById(int id)
        {
            var package = await _packageRepository.GetPackageById(id);
            return package == null ? null : _mapper.Map<PackageDTO>(package);
        }

        public async Task AddPackage(PackageDTO packageDto)
        {
            var package = _mapper.Map<Package>(packageDto);
            await _packageRepository.AddPackage(package);
        }

        public async Task UpdatePackage(PackageDTO packageDto)
        {
            var package = _mapper.Map<Package>(packageDto);
            await _packageRepository.UpdatePackage(package);
        }

        public async Task DeletePackage(int id)
        {
            await _packageRepository.DeletePackage(id);
        }

        public async Task<bool> PackageExists(int id)
        {
            return await _packageRepository.PackageExists(id);
        }


        public async Task<IEnumerable<PackageDTO>> GetPackagesBySubcategoryIdAsync(int subcategoryId)
        {
            var packages = await _packageRepository.GetBySubcategoryIdAsync(subcategoryId);
            return _mapper.Map<IEnumerable<PackageDTO>>(packages);
        }


    }
}
